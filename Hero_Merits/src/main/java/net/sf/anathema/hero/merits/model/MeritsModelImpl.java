package net.sf.anathema.hero.merits.model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.health.model.HealthModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.change.FlavoredChangeListener;
import net.sf.anathema.hero.individual.change.UnspecifiedChangeListener;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.RemovableEntryChangeAdapter;
import net.sf.anathema.hero.merits.compiler.MeritCache;
import net.sf.anathema.hero.merits.model.mechanics.MeritHealthProvider;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.AbstractRemovableEntryModel;
import net.sf.anathema.library.model.RemovableEntryListener;

import org.jmock.example.announcer.Announcer;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class MeritsModelImpl extends AbstractRemovableEntryModel<Merit> implements MeritsModel {

  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private ChangeAnnouncer change;
  private MeritCache meritCache;
  private MeritCategory currentType = MeritCategory.Story;
  private String currentMerit = "";
  private String currentDescription = "";
  private Hero hero;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    this.meritCache = environment.getDataSet(MeritCache.class);
    MeritHealthProvider healthProvider = new MeritHealthProvider(this);
    HealthModelFetcher.fetch(hero).addHealthLevelProvider(healthProvider);
    HealthModelFetcher.fetch(hero).addPainToleranceProvider(healthProvider);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void initializeListening(final ChangeAnnouncer announcer) {
    addModelChangeListener(new UnspecifiedChangeListener(announcer));
    addModelChangeListener((RemovableEntryListener) new RemovableEntryChangeAdapter<>(announcer));
    change = announcer;
  }

  public List<Trait> getContingentTraits() {
    List<Trait> traits = new ArrayList<>();
    TraitTypeFinder typeFinder = new TraitTypeFinder();
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    for (MeritOption merit : meritCache.getAllMeritOptions()) {
      for (String typeLabel : merit.getContingentTraitTypes()) {
        Trait trait = traitModel.getTrait(typeFinder.getTrait(typeLabel));
        if (!traits.contains(trait)) {
          traits.add(trait);
        }
      }
    }
    return traits;
  }

  @Override
  public List<Merit> getMerits() {
    return getEntries();
  }

  @Override
  public List<Merit> getMeritsOfOption(MeritOption option) {
    return getEntries().stream().filter(merit -> merit.getBaseOption().equals(option)).collect(toList());
  }

  @Override
  public List<MeritOption> getCurrentMeritOptions() {
    List<MeritOption> options = meritCache.getAllMeritOptions();
    options.removeIf(item -> item.getType() != currentType || !item.isHeroEligible(hero) ||
            (!item.allowsRepurchase() && hasMerit(item)));
    return options;
  }

  @Override
  public List<MeritOption> getCurrentMeritOptionsOfAllTypes() {
    List<MeritOption> options = meritCache.getAllMeritOptions();
    options.removeIf(item -> !item.isHeroEligible(hero) ||
            (!item.allowsRepurchase() && hasMerit(item)));
    return options;
  }

  @Override
  public List<String> getCurrentMeritOptionLabels() {
    return Lists.transform(getCurrentMeritOptions(), option -> option.getId());
  }

  @Override
  public void setCurrentType(MeritCategory type) {
    this.currentType = type;
    fireEntryChanged();
  }

  @Override
  public void setCurrentMerit(String merit) {
    this.currentMerit = merit;
    fireEntryChanged();
  }

  @Override
  public void setCurrentDescription(String description) {
    this.currentDescription = description;
    fireEntryChanged();
  }

  @Override
  public MeritCategory getCurrentType() {
    return currentType;
  }

  @Override
  public String getCurrentMerit() {
    return currentMerit;
  }

  @Override
  public String getCurrentDescription() {
    return currentDescription;
  }

  @Override
  public void setCurrentMeritOption(MeritOption option) {
    this.currentMerit = option.getId();
    fireEntryChanged();
  }

  @Override
  public MeritOption getCurrentMeritOption() {
    return meritCache.getMeritOptionByName(currentMerit, false);
  }

  @Override
  public void addChangeListener(FlavoredChangeListener listener) {
    hero.getChangeAnnouncer().addListener(listener);
  }

  private boolean hasMerit(MeritOption option) {
    for (Merit merit : getEntries()) {
      if (merit.getBaseOption().equals(option)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected Merit createEntry() {
    MeritImpl merit = new MeritImpl(meritCache.getMeritOptionByName(currentMerit, true),
            currentDescription, hero, isCharacterExperienced());
    merit.addCurrentValueListener(new TraitValueChangedListener(change, merit));
    return merit;
  }

  @Override
  protected boolean isEntryAllowed() {
    if (Strings.isNullOrEmpty(currentMerit)) {
      return false;
    }
    MeritOption baseMerit = meritCache.getMeritOptionByName(currentMerit, false);
    if (baseMerit != null) {
      if (!baseMerit.isHeroEligible(hero) ||
              (!baseMerit.allowsRepurchase() && hasMerit(baseMerit))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void addModelChangeListener(ChangeListener listener) {
    announcer.addListener(listener);
  }

  @Override
  public boolean isCharacterExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }
}