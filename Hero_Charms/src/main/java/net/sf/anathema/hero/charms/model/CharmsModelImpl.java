package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmAttributeList;
import net.sf.anathema.charm.data.martial.MartialArtsLevel;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.context.CreationCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ExperiencedCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ProxyCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.favored.CheapenedChecker;
import net.sf.anathema.hero.charms.model.favored.IsCharmCheapened;
import net.sf.anathema.hero.charms.model.favored.IsFavoredMagic;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.charms.model.learn.CharmLearner;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.model.learn.LearningModelImpl;
import net.sf.anathema.hero.charms.model.learn.MagicLearner;
import net.sf.anathema.hero.charms.model.learn.MartialArtsLearnModel;
import net.sf.anathema.hero.charms.model.learn.MartialArtsLearnModelImpl;
import net.sf.anathema.hero.charms.model.options.CharmOptions;
import net.sf.anathema.hero.charms.model.options.CharmOptionsImpl;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategory;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.rules.CharmsRulesImpl;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmManager;
import net.sf.anathema.hero.charms.model.special.SpecialCharmManager;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteModifyingCharms;
import net.sf.anathema.hero.charms.sheet.content.IMagicStats;
import net.sf.anathema.hero.charms.sheet.content.PrintCharmsProvider;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.charm.data.martial.MartialArtsLevel.Sidereal;
import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.hasLevel;
import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.isFormMagic;
import static net.sf.anathema.charm.data.martial.MartialArtsUtilities.isMartialArts;
import static net.sf.anathema.hero.charms.model.learn.prerequisites.IsAutoSatisfiable.isAutoSatisfiable;
import static net.sf.anathema.hero.charms.model.learn.prerequisites.IsSatisfied.isSatisfied;

public class CharmsModelImpl implements CharmsModel {

  private final ProxyCharmLearnStrategy charmLearnStrategy = new ProxyCharmLearnStrategy(
    new CreationCharmLearnStrategy());
  private LearningModelImpl learningModel = new LearningModelImpl(charmLearnStrategy, this);
  private final CharmsRules charmsRules;
  private ISpecialCharmManager manager;
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private ExperienceModel experience;
  private TraitModel traits;
  private PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private Hero hero;
  private CharmOptionsImpl options;
  private final List<PrintMagicProvider> printMagicProviders = new ArrayList<>();
  private final List<MagicLearner> magicLearners = new ArrayList<>();
  private final IsFavoredMagic isFavoredMagic = new IsFavoredMagic();

  public CharmsModelImpl(CharmsTemplate template) {
    this.charmsRules = new CharmsRulesImpl(template);
  }

  @Override
  public Identifier getId() {
    return CharmsModel.ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    isFavoredMagic.add(new IsCharmCheapened(hero));
    CharmSpecialistImpl specialist = new CharmSpecialistImpl(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.traits = TraitModelFetcher.fetch(hero);
    this.hero = hero;
    CharmProvider provider = environment.getDataSet(CharmCache.class);
    this.options = new CharmOptionsImpl(provider, charmsRules, hero);
    this.manager = new SpecialCharmManager(specialist, hero, this);
    initSpecialCharmConfigurations();
    learnCompulsiveCharms();
    addPrintProvider(new PrintCharmsProvider(hero));
    addLearnProvider(new CharmLearner(this));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    learningModel.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(Charm charm) {
        control.announce().changeOccurred();
      }

      @Override
      public void charmLearned(Charm charm) {
        control.announce().changeOccurred();
      }
    });
    this.experience.addStateChangeListener(() -> {
      if (experience.isExperienced()) {
        charmLearnStrategy.setStrategy(new ExperiencedCharmLearnStrategy());
      } else {
        charmLearnStrategy.setStrategy(new CreationCharmLearnStrategy());
      }
    });
    announcer.addListener(flavor -> {
      verifyCharms();
      control.announce().changeOccurred();
    });
    addCharmLearnListener(new CharacterChangeCharmListener(announcer));
  }

  private void learnCompulsiveCharms() {
    charmsRules.forAllCompulsiveCharms(charmName -> {
      Charm charm = getCharmById(charmName);
      getLearningModel().learnCharm(charm, false);
    });
  }

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    learningModel.addCharmLearnListener(listener);
  }

  private void initSpecialCharmConfigurations() {
    CharmMap charmMap = options.getCharmIdMap();
    for (ISpecialCharm specialCharm : options.getSpecialCharms()) {
      Charm charm = charmMap.getCharmById(specialCharm.getCharmName());
      if (charm == null) {
        continue;
      }
      manager.registerSpecialCharmConfiguration(specialCharm, charm, learningModel);
    }
  }

  @Override
  public Collection<CharmTree> getAllTrees() {
    List<CharmTree> allTrees = new ArrayList<>();
    for (CharmTreeCategory category : options) {
      allTrees.addAll(category.getAllCharmTrees());
    }
    return allTrees;
  }

  @Override
  public Collection<CharmTree> getTreesFor(CategoryReference reference) {
    for (CharmTreeCategory category : options) {
      if (category.getReference().equals(reference)) {
        return category.getAllCharmTrees();
      }
    }
    return Collections.emptyList();
  }

  @Override
  public Charm getCharmById(CharmName charmId) {
    Charm charm = options.getCharmIdMap().getCharmById(charmId);
    if (charm != null) {
      return charm;
    }
    throw new IllegalArgumentException("No charm found for id \"" + charmId.text + "\"");
  }

  @Override
  public CharmSpecialsModel getCharmSpecialsModel(Charm charm) {
    return manager.getSpecialCharmConfiguration(charm);
  }

  @Override
  public void forgetAllAlienCharms() {
    for (CharmTreeCategory category : options) {
      if (charmsRules.isAlienCategory(category.getReference())) {
        learningModel.forgetAll(category.getReference());
      } else {
        learningModel.forgetExclusives(category.getReference());
      }
    }
  }

  private void verifyCharms() {
    if (!hero.isFullyLoaded()) {
      return;
    }
    List<Charm> charmsToUnlearn = new ArrayList<>();
    for (Charm charm : learningModel.getCharmsLearnedEitherWay()) {
      boolean prerequisitesForCharmAreNoLongerMet = !isLearnable(charm);
      boolean charmCanBeUnlearned = isForgettable(charm);
      if (prerequisitesForCharmAreNoLongerMet && charmCanBeUnlearned) {
        charmsToUnlearn.add(charm);
      }
    } for (Charm charm : charmsToUnlearn) {
      boolean learnedAtCreation = learningModel.isLearnedOnCreation(charm);
      boolean learnedWithExperience = !learnedAtCreation;
      learningModel.forgetCharm(charm, learnedWithExperience);
    }
  }

  @Override
  public void addLearnableListener(ChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public final boolean isLearnable(Charm charm) {
    if (isAlienCharm(charm)) {
      CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
      if (!(charmsRules.isAllowedAlienCharms(casteType))) {
        return false;
      }
      if (charm.hasAttribute(CharmAttributeList.NATIVE)) {
        return false;
      }
    }
    if (isMartialArts(charm)) {
      boolean isSiderealFormCharm = isFormMagic(charm) && hasLevel(Sidereal, charm);
      MartialArtsLearnModel martialArtsConfiguration = new MartialArtsLearnModelImpl(this);
      if (isSiderealFormCharm && !martialArtsConfiguration.isAnyCelestialStyleCompleted()) {
        return false;
      }
      if (!charmsRules.getMartialArtsRules().isCharmAllowed(charm, isExperienced())) {
        return false;
      }
    }
    for (CharmPrerequisite prerequisite : charm.getPrerequisites().getCharmPrerequisites()) {
      if (!isSatisfied(prerequisite, this) && !isAutoSatisfiable(prerequisite, this)) {
        return false;
      }
    }
    CharmTraitRequirementChecker traitRequirementChecker = new CharmTraitRequirementChecker(
      getPrerequisiteModifyingCharms(), traits, this);
    return traitRequirementChecker.areTraitMinimumsSatisfied(charm);
  }

  @Override
  public boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold) {
    int count = 0;
    for (Charm charm : learningModel.getCurrentlyLearnedCharms()) {
      if (charm.hasAttribute(attribute)) {
        count++;
      }
      if (count >= threshold) {
        return true;
      }
    }
    return false;
  }

  private boolean isExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }

  private PrerequisiteModifyingCharms getPrerequisiteModifyingCharms() {
    if (prerequisiteModifyingCharms == null) {
      this.prerequisiteModifyingCharms = new PrerequisiteModifyingCharms(options.getSpecialCharms());
    }
    return prerequisiteModifyingCharms;
  }

  @Override
  public boolean isLearned(CharmName charmId) {
    Charm charm = getCharmById(charmId);
    return charm != null && isLearned(charm);
  }

  public final boolean isForgettable(Charm charm) {
    return getLearningModel().isForgettable(charm);
  }

  @Override
  public final boolean isLearned(Charm charm) {
    return getLearningModel().isCurrentlyLearned(charm);
  }

  @Override
  public void addCheapenedChecker(CheapenedChecker cheapenedChecker) {
    isFavoredMagic.add(cheapenedChecker);
  }

  @Override
  public void addPrintProvider(PrintMagicProvider provider) {
    printMagicProviders.add(provider);
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    for (PrintMagicProvider provider : printMagicProviders) {
      provider.addPrintMagic(printMagic);
    }
  }

  @Override
  public void addLearnProvider(MagicLearner provider) {
    magicLearners.add(provider);
  }

  @Override
  public LearningModel getLearningModel() {
    return learningModel;
  }

  @Override
  public MagicCreationCostEvaluator getMagicCostEvaluator() {
    return new MagicCreationCostEvaluator(magicLearners);
  }

  @Override
  public boolean isAlienCharm(Charm charm) {
    return charmsRules.isAlienCharm(charm);
  }

  @Override
  public boolean isMagicCheapened(Magic magic) {
    return isFavoredMagic.isFavored(magic);
  }

  @Override
  public final boolean isCompulsiveCharm(Charm charm) {
    return charmsRules.isCompulsiveCharm(charm);
  }

  public MartialArtsLevel getStandardMartialArtsLevel() {
    return charmsRules.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public CharmOptions getOptions() {
    return options;
  }
}