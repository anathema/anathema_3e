package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.charms.advance.experience.CharmExperienceModel;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.additional.AdditionalCharmRules;
import net.sf.anathema.hero.charms.model.context.CreationCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ExperiencedCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ProxyCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.favored.IsCharmCheapened;
import net.sf.anathema.hero.charms.model.learn.CharmLearnAdapter;
import net.sf.anathema.hero.charms.model.learn.CharmLearner;
import net.sf.anathema.hero.charms.model.learn.Charms;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.learn.LearningModel;
import net.sf.anathema.hero.charms.model.learn.LearningModelImpl;
import net.sf.anathema.hero.charms.model.options.CharmOptions;
import net.sf.anathema.hero.charms.model.options.CharmOptionsImpl;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategory;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charms.model.rules.CharmsRulesImpl;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmManager;
import net.sf.anathema.hero.charms.model.special.SpecialCharmManager;
import net.sf.anathema.hero.charms.sheet.content.PrintCharmsProvider;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.magic.advance.MagicPointsModel;
import net.sf.anathema.hero.magic.advance.MagicPointsModelFetcher;
import net.sf.anathema.hero.magic.advance.experience.MagicExperienceCostCalculator;
import net.sf.anathema.hero.magic.advance.experience.MagicExperienceData;
import net.sf.anathema.hero.magic.model.MagicModelFetcher;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.CharmAttributeList;
import net.sf.anathema.magic.data.attribute.MagicAttribute;
import net.sf.anathema.magic.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.magic.data.reference.TreeReference;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.sf.anathema.hero.charms.model.learn.prerequisites.IsAutoSatisfiable.isAutoSatisfiable;
import static net.sf.anathema.hero.charms.model.learn.prerequisites.IsSatisfied.isSatisfied;
import static net.sf.anathema.hero.magic.model.CommonMagicAttributes.NO_PURCHASE;
import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;

public class CharmsModelImpl implements CharmsModel {

  private final ProxyCharmLearnStrategy charmLearnStrategy = new ProxyCharmLearnStrategy(
          new CreationCharmLearnStrategy());
  private LearningModelImpl learningModel = new LearningModelImpl(charmLearnStrategy, this);
  private final CharmsRules charmsRules;
  private ISpecialCharmManager manager;
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private ExperienceModel experience;
  private TraitModel traits;
  private Hero hero;
  private CharmOptionsImpl options;
  private final CharmsTemplate template;

  public CharmsModelImpl(CharmsTemplate template) {
    this.charmsRules = new CharmsRulesImpl(template);
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return CharmsModel.ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.traits = TraitModelFetcher.fetch(hero);
    this.hero = hero;
    this.options = new CharmOptionsImpl(environment.getDataSet(CharmCache.class), charmsRules, hero);
    this.manager = new SpecialCharmManager(new CharmSpecialistImpl(hero), hero, this);
    initSpecialLearningCharms();
    initPoints(hero);
    initAdditionalRules(environment, hero);
    initPrint(hero);
    options.initSpecialMechanics();
  }

  private void initPrint(Hero hero) {
    MagicModelFetcher.fetch(hero).addPrintProvider(new PrintCharmsProvider(hero));
  }

  private void initAdditionalRules(HeroEnvironment environment, Hero hero) {
    Collection<AdditionalCharmRules> additionalRules = environment.getObjectFactory()
            .instantiateAllImplementers(AdditionalCharmRules.class, hero);
    additionalRules.stream().filter(rules -> template.additionalCharmRules.contains(rules.getId()))
            .forEach(AdditionalCharmRules::initialize);
  }

  private void initPoints(Hero hero) {
    MagicPointsModel magicPointsModel = MagicPointsModelFetcher.fetch(hero);
    magicPointsModel.registerMagicLearner(new CharmLearner(this));
    magicPointsModel.addCheapenedChecker(new IsCharmCheapened(hero));
    MagicExperienceData experienceCost = magicPointsModel.getExperienceCost();
    MagicExperienceCostCalculator calculator = new MagicExperienceCostCalculator(experienceCost);
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addToExperienceOverview(new CharmExperienceModel(calculator, hero));
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

  @Override
  public void addCharmLearnListener(ICharmLearnListener listener) {
    learningModel.addCharmLearnListener(listener);
  }

  private void initSpecialLearningCharms() {
    CharmMap charmMap = options.getCharmIdMap();
    for (CharmSpecialLearning specialCharm : options.getSpecialLearningCharms()) {
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
  public boolean exists(CharmName charmId) {
    return options.getCharmIdMap().exists(charmId);
  }

  @Override
  public CharmSpecialLearningModel getCharmSpecialLearningModel(Charm charm) {
    return manager.getSpecialCharmConfiguration(charm);
  }

  @Override
  public void forgetAllAlienCharms() {
    for (CharmTreeCategory category : options) {
      if (charmsRules.isAlienCategory(category.getReference())) {
        learningModel.forgetAll(category.getReference());
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
    }
    for (Charm charm : charmsToUnlearn) {
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
      if (!charm.hasAttribute(CharmAttributeList.ECLIPSE_ATTRIBUTE)) {
        return false;
      }
    }
    SatisfactionConsumer isSatisfiedOrSatisfiable = new SatisfactionConsumer();
    charm.getPrerequisites().forEachCharmPrerequisite(isSatisfiedOrSatisfiable);
    if (!isSatisfiedOrSatisfiable.satisfied) {
      return false;
    }
    CharmTraitRequirementCalculator calculator = new CharmTraitRequirementCalculator(new TraitStateFetcher(hero));
    CharmTraitRequirementChecker checker = new CharmTraitRequirementChecker(calculator, traits);
    return checker.areTraitMinimumsSatisfied(charm);
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

  @Override
  public boolean hasLearnedThresholdCharmsWithKeywordFromTree(
          TreeReference tree, MagicAttribute attribute, int threshold) {
    int count = 0;
    Predicate<Charm> charmsFromThisTree = charm -> charm.getTreeReference().equals(tree);
    for (Charm charm : learningModel.getCurrentlyLearnedCharms().applyFilter(charmsFromThisTree)) {
      if (charm.hasAttribute(attribute)) {
        count++;
      }
      if (count >= threshold) {
        return true;
      }
    }
    return false;
  }


  @Override
  public boolean hasLearnedThresholdCharmsOfTrait(List<TraitType> requiredTraits,
                                                  CategoryReference category, int threshold, int minimumEssence) {
    Charms charmsForTraits = findCharmsMatchingTraits(requiredTraits, category);
    int numberOfCharmsMatchingEssence = countCharmsThatMatchTheMinimumEssence(minimumEssence, charmsForTraits);
    return numberOfCharmsMatchingEssence >= threshold;
  }

  private int countCharmsThatMatchTheMinimumEssence(int minimumEssence, Charms matchingLearnedCharms) {
    List<Charm> matchingCharms = new ArrayList<>();
    for (Charm charm : matchingLearnedCharms) {
      charm.getPrerequisites().forEachTraitPrerequisite(trait -> {
        if (!(trait.type.type.equals(Essence.getId()))) {
          return;
        }
        if (trait.minimalValue >= minimumEssence) {
          matchingCharms.add(charm);
        }
      });
    }
    return matchingCharms.size();
  }

  @SuppressWarnings("SimplifiableIfStatement")
  private Charms findCharmsMatchingTraits(List<TraitType> requiredTraits, CategoryReference category) {
    Charms learnedCharms = getLearningModel().getCurrentlyLearnedCharms();
    return learnedCharms.applyFilter(charm -> {
      if (charm.hasAttribute(NO_PURCHASE)) {
        return false;
      }
      if (category != null && !category.equals(charm.getTreeReference().category)) {
        return false;
      }
      return requiredTraits.contains(new TraitType(charm.getPrerequisites().getPrimaryTraitType().type));
    });
  }

  @Override
  public boolean hasLearnedThresholdCharmsOfAnyOneTrait(int threshold) {
    Map<RequiredTraitType, Integer> groupCounts = new HashMap<>();

    for (Charm charm : getLearningModel().getCurrentlyLearnedCharms()) {
      RequiredTraitType group = charm.getPrerequisites().getPrimaryTraitType();
      Integer currentCount = groupCounts.get(group);
      if (currentCount == null) {
        currentCount = 0;
        groupCounts.put(group, currentCount);
      }
      if (++currentCount >= threshold) {
        return true;
      }
    }

    return false;
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
  public LearningModel getLearningModel() {
    return learningModel;
  }

  @Override
  public boolean isAlienCharm(Charm charm) {
    return charmsRules.isAlienCharm(charm);
  }

  @Override
  public CharmOptions getOptions() {
    return options;
  }

  private class SatisfactionConsumer implements Consumer<CharmPrerequisite> {

    boolean satisfied = true;

    @Override
    public void accept(CharmPrerequisite prerequisite) {
      if (!isSatisfied(prerequisite, CharmsModelImpl.this) && !isAutoSatisfiable(prerequisite, CharmsModelImpl.this)) {
        satisfied = false;
      }
    }
  }
}