package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.abilities.advance.creation.AbilityCostCalculatorImpl;
import net.sf.anathema.hero.abilities.advance.creation.AbilityCreationData;
import net.sf.anathema.hero.abilities.advance.creation.ConfigurableAbilityTraitPickModel;
import net.sf.anathema.hero.abilities.advance.creation.DefaultAbilityBonusModel;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceCalculator;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceData;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceModel;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperiencePointEntry;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplate;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.experience.model.ExperienceModel;
import net.sf.anathema.hero.experience.model.ExperienceModelFetcher;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.event.IntegerChangedListener;
import net.sf.anathema.library.event.IntegerChangingListener;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.model.overview.SpendingModel;
import net.sf.anathema.points.model.overview.WeightedCategory;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;

public class AbilitiesPointModelImpl implements AbilitiesPointModel {

  public static final Identifier ID = new SimpleIdentifier("AbilitiesPoints");
  private AbilityPointsTemplate template;
  private final GroupedCalculationTraitHolder traitHolder = new GroupedCalculationTraitHolder();

  public AbilitiesPointModelImpl(AbilityPointsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    initializeTraitHandling(hero);
    initializeBonusPoints(hero);
    initializeExperiencePoints(hero);
  }

  private void initializeTraitHandling(Hero hero) {
    AbilityPointTraitHolder traitHolder = new AbilityPointTraitHolder(AbilitiesModelFetcher.fetch(hero));
    this.traitHolder.add(traitHolder);
  }

  private void initializeBonusPoints(Hero hero) {
    AbilityCostCalculatorImpl abilityCalculator = createCalculator();
    initializeBonusCalculator(hero, abilityCalculator);
    initializeBonusOverview(hero, abilityCalculator);
  }

  private void initializeBonusCalculator(Hero hero, AbilityCostCalculatorImpl abilityCalculator) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addBonusPointCalculator(abilityCalculator);
  }

  private void initializeBonusOverview(Hero hero, AbilityCostCalculatorImpl abilityCalculator) {
    AbilitiesModel abilities = AbilitiesModelFetcher.fetch(hero);
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addBonusCategory(new WeightedCategory(200, "Abilities"));
    addOnlyModelWithAllotment(pointsModel, new DefaultAbilityBonusModel(abilityCalculator, getCreationData()));
    for (TraitStateType state : abilities.getAvailableTraitStates()) {
      addOnlyModelWithAllotment(pointsModel,
              new ConfigurableAbilityTraitPickModel(abilityCalculator, abilities.getTraitPicksForState(state), state));
    }
  }

  private void addOnlyModelWithAllotment(PointsModel pointsModel, SpendingModel spendingModel) {
    if (spendingModel.getAllotment() > 0) {
      pointsModel.addToBonusOverview(spendingModel);
    }
  }

  private void initializeExperiencePoints(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AbilityExperienceData experienceData = new AbilityExperienceData(template);
    AbilityExperienceCalculator calculator = new AbilityExperienceCalculator(experienceData);
    pointsModel.addToExperienceOverview(new AbilityExperienceModel(traitHolder, calculator));
    
    ExperienceModel experienceModel = ExperienceModelFetcher.fetch(hero);
    this.traitHolder.getAll().forEach(trait ->
    		trait.addChangingValueListener(new IntegerChangingListener() {

					@Override
					public void valueChanged(int initialValue, int newValue) {
						if (experienceModel.isExperienced() && newValue > initialValue)
						{
							ExperiencePointEntry entry = new AbilityExperiencePointEntry(
									trait,
									experienceData.getAbilityCosts(traitHolder.getState(trait).isCheapened()),
									initialValue,
									newValue
									);
							pointsModel.getExperiencePoints().addEntry(entry);
						}
					}
    		}));
  }

  private AbilityCostCalculatorImpl createCalculator() {
    AbilityCreationData creationData = getCreationData();
    return new AbilityCostCalculatorImpl(traitHolder, creationData);
  }

  private AbilityCreationData getCreationData() {
    return new AbilityCreationData(template);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void add(PointCalculationTraitHolder holder) {
    traitHolder.add(holder);
  }
}
