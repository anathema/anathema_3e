package net.sf.anathema.hero.equipment;

import static net.sf.anathema.equipment.database.gson.GsonEquipmentDatabase.DATABASE_FOLDER;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.character.UnarmedModificationProvider;
import net.sf.anathema.equipment.database.IEquipmentTemplateProvider;
import net.sf.anathema.equipment.database.gson.GsonEquipmentDatabase;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.IArtifactStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.template.IEquipmentTemplate;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.equipment.model.CharacterStatsModifiers;
import net.sf.anathema.hero.equipment.model.EquipmentCollection;
import net.sf.anathema.hero.equipment.model.EquipmentDirectAccess;
import net.sf.anathema.hero.equipment.model.EquipmentHeroEvaluatorImpl;
import net.sf.anathema.hero.equipment.model.EquipmentItem;
import net.sf.anathema.hero.equipment.model.natural.DefaultNaturalSoak;
import net.sf.anathema.hero.equipment.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.hero.equipment.model.natural.UnarmedModificationProviderCollection;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.UnspecifiedChangeListener;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;
import net.sf.anathema.hero.sheet.pdf.content.stats.StatsModelFetcher;
import net.sf.anathema.hero.specialties.model.Specialty;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModelFetcher;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.event.CollectionListener;
import net.sf.anathema.library.identifier.Identifier;

import org.jmock.example.announcer.Announcer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class EquipmentModelImpl implements EquipmentOptionsProvider, EquipmentModel {
  private final EquipmentCollection naturalWeapons = new EquipmentCollection();
  private final Table<IEquipmentItem, IEquipmentStats, List<IEquipmentStatsOption>> optionsTable = HashBasedTable.create();
  private final Announcer<ChangeListener> modelChangeControl = Announcer.to(ChangeListener.class);
  private final Announcer<CollectionListener> equipmentItemControl = Announcer.to(CollectionListener.class);
  private final EquipmentCollection equipmentItems = new EquipmentCollection();
  private final UnarmedModificationProviderCollection unarmedProviders = new UnarmedModificationProviderCollection();
  private IEquipmentTemplateProvider equipmentTemplateProvider;
  private final ChangeListener itemChangePropagator = this::fireModelChanged;
  private EquipmentHeroEvaluator dataProvider;
  private IArmourStats naturalArmor;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    StatsModelFetcher.fetch(hero).addModifierFactory(this);
    this.equipmentTemplateProvider = loadEquipmentDatabase(environment);
    this.naturalArmor = determineNaturalArmor(hero);
    this.dataProvider = new EquipmentHeroEvaluatorImpl(hero);
    createNaturalWeapons();
    new SpecialtiesCollectionImpl(hero).addSpecialtyListChangeListener(new SpecialtyPrintRemover(dataProvider));
    EssencePoolModelFetcher.fetch(hero).addEssencePoolModifier(this);
  }
  
  public void addUnarmedModification(UnarmedModificationProvider provider) {
  	unarmedProviders.addProvider(provider);
  }

  private void createNaturalWeapons() {
    IEquipmentItem item = createItem(new NaturalWeaponTemplate(unarmedProviders));
    naturalWeapons.add(item);
  }

  private GsonEquipmentDatabase loadEquipmentDatabase(HeroEnvironment environment) {
    Path dataBaseDirectory = environment.getDataFileProvider().getDataBaseDirectory(DATABASE_FOLDER);
    EquipmentDirectAccess access = new EquipmentDirectAccess(dataBaseDirectory);
    return new GsonEquipmentDatabase(access);
  }

  private IArmourStats determineNaturalArmor(Hero hero) {
    Trait stamina = TraitModelFetcher.fetch(hero).getTrait(AttributeType.Stamina);
    return new DefaultNaturalSoak(stamina);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    addChangeListener(new UnspecifiedChangeListener(announcer));
  }

  @Override
  public EquipmentHeroEvaluator getHeroEvaluator() {
    return dataProvider;
  }

  @Override
  public EquipmentOptionsProvider getOptionProvider() {
    return this;
  }

  @Override
  public IArmourStats getNaturalArmor() {
    return naturalArmor;
  }

  @Override
  public Collection<IEquipmentItem> getNaturalWeapons() {
    return naturalWeapons.asList();
  }

  @Override
  public boolean canBeRemoved(IEquipmentItem item) {
    return !naturalWeapons.contains(item);
  }

  @Override
  public String[] getAvailableTemplateIds() {
    return equipmentTemplateProvider.getAllAvailableTemplateIds();
  }

  private IEquipmentTemplate loadEquipmentTemplate(String templateId) {
    return equipmentTemplateProvider.loadTemplate(templateId);
  }

  private IEquipmentItem getNaturalWeapon(String templateId) {
    for (IEquipmentItem item : naturalWeapons) {
      if (templateId.equals(item.getTemplateId())) {
        return item;
      }
    }
    return null;
  }

  private List<IEquipmentStatsOption> getOptionsList(IEquipmentItem item, IEquipmentStats stats) {
    List<IEquipmentStatsOption> list = optionsTable.get(item, stats);
    if (list == null) {
      list = new ArrayList<>();
      optionsTable.put(item, stats, list);
    }
    return list;
  }

  @Override
  public void enableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) {
      return;
    }
    getOptionsList(item, stats).add(option);
    fireModelChanged();
  }

  @Override
  public void disableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) {
      return;
    }
    getOptionsList(item, stats).remove(option);
    fireModelChanged();
  }

  @Override
  public boolean isStatOptionEnabled(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    return item != null && stats != null && getOptionsList(item, stats).contains(option);
  }

  @Override
  public StatsOptions getEnabledStatOptions(IEquipmentItem item, IEquipmentStats stats) {
    if (item == null || stats == null) {
      return new StatsOptions();
    }
    List<IEquipmentStatsOption> options = getOptionsList(item, stats);
    return new StatsOptions(options);
  }

  @Override
  public StatsOptions getEnabledStatOptions(IEquipmentStats stats) {
    if (stats == null) {
      return new StatsOptions();
    }
    List<IEquipmentItem> itemList = new ArrayList<>();
    itemList.addAll(getNaturalWeapons());
    itemList.addAll(getEquipmentItems());
    for (IEquipmentItem item : itemList) {
      for (IEquipmentStats stat : item.getStats()) {
        if (stats.equals(stat)) {
          return getEnabledStatOptions(item, stat);
        }
      }
    }
    return new StatsOptions();
  }

  @Override
  public boolean transferOptions(IEquipmentItem fromItem, IEquipmentItem toItem) {
    boolean transferred = false;
    if (fromItem != null && toItem != null) {
      for (IEquipmentStats fromStats : fromItem.getStats()) {
        List<IEquipmentStatsOption> specialtyList = optionsTable.remove(fromItem, fromStats);
        boolean printCheckboxEnabled = fromItem.isPrintEnabled(fromStats);
        IEquipmentStats toStats = toItem.getStat(fromStats.getId());

        if (toStats != null) {
          transferred = true;
          if (specialtyList != null) {
            optionsTable.put(toItem, toStats, specialtyList);
          }
          toItem.setPrintEnabled(toStats, printCheckboxEnabled);
        }
      }
    }
    return transferred;
  }

  @Override
  public Collection<IEquipmentItem> getEquipmentItems() {
    return equipmentItems.asList();
  }

  @Override
  public IEquipmentItem addItem(String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    if (template == null) {
      return getNaturalWeapon(templateId);
    }
    return addManMadeObject(template);
  }

  private IEquipmentItem addManMadeObject(IEquipmentTemplate template) {
    IEquipmentItem item = createItem(template);
    equipmentItems.add(item);
    announceItemAndListenForChanges(item);
    return item;
  }

  private IEquipmentItem createItem(IEquipmentTemplate template) {
    return new EquipmentItem(template);
  }

  @Override
  public void removeItem(IEquipmentItem item) {
    equipmentItems.remove(item);
    announce().itemRemoved();
    item.removeChangeListener(itemChangePropagator);
    fireModelChanged();
  }

  @SuppressWarnings("unchecked")
  private CollectionListener announce() {
    return equipmentItemControl.announce();
  }

  private void fireModelChanged() {
    modelChangeControl.announce().changeOccurred();
  }

  @Override
  public void refreshItems() {
    for (IEquipmentItem item : equipmentItems) {
      if (canBeRemoved(item)) {
        IEquipmentItem refreshedItem = refreshItem(item);
        if (refreshedItem != null) {
          refreshedItem.setPersonalization(item);
          getOptionProvider().transferOptions(item, refreshedItem);
          announceItemAndListenForChanges(refreshedItem);
        }
      }
    }
  }

  private void announceItemAndListenForChanges(IEquipmentItem refreshedItem) {
    announce().itemAdded();
    refreshedItem.addChangeListener(itemChangePropagator);
    fireModelChanged();
  }

  private IEquipmentItem refreshItem(IEquipmentItem item) {
    String templateId = item.getTemplateId();
    removeItem(item);
    return addItem(templateId);
  }

  @Override
  public void addEquipmentObjectListener(CollectionListener listener) {
    equipmentItemControl.addListener(listener);
  }

  private void addChangeListener(ChangeListener listener) {
    modelChangeControl.addListener(listener);
  }

  @Override
  public int getMotesExpended() {
    int total = 0;
    for (IEquipmentItem item : equipmentItems) {
      for (IEquipmentStats stats : item.getStats()) {
        if (stats instanceof IArtifactStats && item.isAttuned()) {
          total += ((IArtifactStats) stats).getAttuneCost();
        }
      }
    }
    return total;
  }

  @Override
  public HeroStatsModifiers createStatsModifiers(Hero hero) {
    return CharacterStatsModifiers.extractFromCharacter(hero);
  }

  private class SpecialtyPrintRemover implements ChangeListener {
    private final EquipmentHeroEvaluator dataProvider;

    public SpecialtyPrintRemover(EquipmentHeroEvaluator dataProvider) {
      this.dataProvider = dataProvider;
    }

    @Override
    public void changeOccurred() {
      for (IEquipmentItem item : equipmentItems) {
        for (IEquipmentStats stats : item.getStats()) {
          List<IEquipmentStatsOption> list = optionsTable.get(item, stats);
          if (list == null) {
            return;
          }
          List<IEquipmentStatsOption> optionsList = new ArrayList<>(list);
          for (IEquipmentStatsOption option : optionsList) {
            if (!characterStillHasCorrespondingSpecialty(option)) {
              disableStatOption(item, stats, option);
            }
          }
        }
      }
    }

    private boolean characterStillHasCorrespondingSpecialty(IEquipmentStatsOption option) {
      AbilityType trait = AbilityType.valueOf(option.getType());
      Collection<Specialty> specialties = dataProvider.getSpecialties(trait);
      return specialties.contains(option.getUnderlyingTrait());
    }
  }

  @Override
  public String[] getAllAvailableTemplateIds() {
    return equipmentTemplateProvider.getAllAvailableTemplateIds();
  }

  @Override
  public IEquipmentTemplate loadTemplate(String templateId) {
    return equipmentTemplateProvider.loadTemplate(templateId);
  }
}