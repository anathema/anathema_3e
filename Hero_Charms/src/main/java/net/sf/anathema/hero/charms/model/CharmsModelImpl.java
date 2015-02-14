package net.sf.anathema.hero.charms.model;

import static net.sf.anathema.hero.charms.model.learn.prerequisites.IsAutoSatisfiable.isAutoSatisfiable;
import static net.sf.anathema.hero.charms.model.learn.prerequisites.IsSatisfied.isSatisfied;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmAttributeList;
import net.sf.anathema.charm.data.prerequisite.CharmPrerequisite;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.advance.MagicPointsModelFetcher;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.hero.charms.model.additional.AdditionalCharmRules;
import net.sf.anathema.hero.charms.model.context.CreationCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ExperiencedCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.context.ProxyCharmLearnStrategy;
import net.sf.anathema.hero.charms.model.favored.CheapenedChecker;
import net.sf.anathema.hero.charms.model.favored.IsCharmCheapened;
import net.sf.anathema.hero.charms.model.favored.IsCheapenedMagic;
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
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmManager;
import net.sf.anathema.hero.charms.model.special.SpecialCharmManager;
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
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.library.event.ChangeListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import org.jmock.example.announcer.Announcer;

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
  private final List<PrintMagicProvider> printMagicProviders = new ArrayList<>();
  private final IsCheapenedMagic isCheapenedMagic = new IsCheapenedMagic();
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
    isCheapenedMagic.add(new IsCharmCheapened(hero));
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.traits = TraitModelFetcher.fetch(hero);
    this.hero = hero;
    this.options = new CharmOptionsImpl(environment.getDataSet(CharmCache.class), charmsRules, hero);
    this.manager = new SpecialCharmManager(new CharmSpecialistImpl(hero), hero, this);
    initSpecialCharms();
    learnCompulsiveCharms();
    addPrintProvider(new PrintCharmsProvider(hero));
    MagicPointsModelFetcher.fetch(hero).registerMagicLearner(new CharmLearner(this));
    
    Collection<AdditionalCharmRules> additionalRules = environment.getObjectFactory()
    		.instantiateAllImplementers(AdditionalCharmRules.class, this, hero);
    additionalRules.stream().filter(rules -> template.additionalCharmRules.contains(rules.getId()))
    	.forEach(rules -> rules.initialize());;
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

  private void initSpecialCharms() {
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
      } else if (!charm.hasAttribute(CharmAttributeList.ECLIPSE_ATTRIBUTE)) {
        return false;
      }
    }
    for (CharmPrerequisite prerequisite : charm.getPrerequisites().getCharmPrerequisites()) {
      if (!isSatisfied(prerequisite, this) && !isAutoSatisfiable(prerequisite, this)) {
        return false;
      }
    }
    CharmTraitRequirementChecker traitRequirementChecker = new CharmTraitRequirementChecker(
    		new CharmTraitRequirementCalculator(new TraitStateFetcher(hero)), traits);
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
		int count = 0;
		TraitTypeFinder finder = new TraitTypeFinder();
		Charms learnedCharms = getLearningModel().getCurrentlyLearnedCharms();
		Charms matchingLearnedCharms = learnedCharms.applyFilter(charm ->
			!charm.hasAttribute(CommonMagicAttributes.NO_MANUAL_CONTROL) &&
			requiredTraits.contains(finder.getTrait(charm.getPrerequisites().getPrimaryTraitType().type)) &&
			(category == null || category.equals(charm.getTreeReference().category)));
		for (Charm charm : matchingLearnedCharms) {
			boolean meetsEssence = true;
			for (TraitPrerequisite trait : charm.getPrerequisites().getTraitPrerequisites()) {
				if (trait.type.equals(OtherTraitType.Essence.getId()) &&
						traits.getTrait(OtherTraitType.Essence).getCurrentValue() < trait.minimalValue) {
					meetsEssence = false;
				}
				if (meetsEssence && ++count >= threshold) {
					return true;
				}
			}
		}

		return false;
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
  public void addCheapenedChecker(CheapenedChecker cheapenedChecker) {
    isCheapenedMagic.add(cheapenedChecker);
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
  public LearningModel getLearningModel() {
    return learningModel;
  }

  @Override
  public boolean isAlienCharm(Charm charm) {
    return charmsRules.isAlienCharm(charm);
  }

  @Override
  public boolean isMagicCheapened(Magic magic) {
    return isCheapenedMagic.isFavored(magic);
  }

  @Override
  public final boolean isCompulsiveCharm(Charm charm) {
    return charmsRules.isCompulsiveCharm(charm);
  }

  @Override
  public CharmOptions getOptions() {
    return options;
  }
}