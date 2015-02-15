package net.sf.anathema.hero.merits.model;

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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class MeritsModelImpl extends AbstractRemovableEntryModel<Merit> implements MeritsModel {

  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private ChangeAnnouncer change;
  private MeritCache meritCache;
  private MeritCategory currentType = MeritCategory.Story;
  private MeritOption currentMerit;
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
  public boolean hasMeritsMatchingReference(MeritReference reference) {
    return !getMeritsMatchingReference(reference).isEmpty();
  }

  @Override
  public List<Merit> getMeritsMatchingReference(MeritReference reference) {
    Predicate<Merit> referencedMerits = merit -> merit.getBaseOption().isReferencedBy(reference);
    return getEntries().stream().filter(referencedMerits).collect(toList());
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
  public void setCurrentType(MeritCategory type) {
    this.currentType = type;
    fireEntryChanged();
  }

  @Override
  public void setCurrentMeritOption(MeritOption option) {
    this.currentMerit = option;
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
  public MeritOption getCurrentMeritOption() {
    return currentMerit;
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
    MeritImpl merit = new MeritImpl(currentMerit, currentDescription, hero, isCharacterExperienced());
    merit.addCurrentValueListener(new TraitValueChangedListener(change, merit));
    return merit;
  }

  @Override
  protected boolean isEntryAllowed() {
    if (currentMerit == null) {
      return false;
    }
    MeritOption baseMerit = currentMerit;
    if (!baseMerit.isHeroEligible(hero) ||
            (!baseMerit.allowsRepurchase() && hasMerit(baseMerit))) {
      return false;
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

  @Override
  public void resetCurrentMerit() {
    currentDescription = "";
    selectFirstMeritOption();
  }

  @Override
  public MeritOption findOptionByReference(MeritReference reference) {
    return meritCache.getMeritOptionByName(reference);
  }

  private void selectFirstMeritOption() {
    List<MeritOption> currentMeritOptions = getCurrentMeritOptions();
    if (currentMeritOptions.isEmpty()) {
      currentMerit = null;
      return;
    }
    currentMerit = currentMeritOptions.get(0);
  }
}