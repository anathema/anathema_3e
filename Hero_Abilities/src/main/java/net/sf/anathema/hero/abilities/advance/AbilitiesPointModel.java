package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.abilities.advance.creation.AbilityCostCalculatorImpl;
import net.sf.anathema.hero.abilities.advance.creation.AbilityCreationData;
import net.sf.anathema.hero.abilities.advance.creation.CasteAbilityPickModel;
import net.sf.anathema.hero.abilities.advance.creation.DefaultAbilityBonusModel;
import net.sf.anathema.hero.abilities.advance.creation.FavoredAbilityBonusModel;
import net.sf.anathema.hero.abilities.advance.creation.FavoredAbilityPickModel;
import net.sf.anathema.hero.abilities.advance.creation.SupernalAbilityPickModel;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceCalculator;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceData;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModelFetcher;
import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplate;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.model.overview.SpendingModel;
import net.sf.anathema.points.model.overview.WeightedCategory;

public class AbilitiesPointModel implements HeroModel {

  public static final Identifier ID = new SimpleIdentifier("AbilitiesPoints");
  private AbilityPointsTemplate template;

  public AbilitiesPointModel(AbilityPointsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    initializeBonusPoints(hero);
    initializeExperiencePoints(hero);
  }

  private void initializeBonusPoints(Hero hero) {
    AbilityCostCalculatorImpl abilityCalculator = createCalculator(hero);
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
    addOnlyModelWithAllotment(pointsModel, new FavoredAbilityBonusModel(abilityCalculator, getCreationData()));
    addOnlyModelWithAllotment(pointsModel, new FavoredAbilityPickModel(abilityCalculator, abilities.getTraitPicksForState(
      TraitStateType.Favored)));
    addOnlyModelWithAllotment(pointsModel, new CasteAbilityPickModel(abilityCalculator, abilities.getTraitPicksForState(
      TraitStateType.Caste)));
    addOnlyModelWithAllotment(pointsModel, new SupernalAbilityPickModel(abilityCalculator, abilities.getTraitPicksForState(
      TraitStateType.Supernal)));
  }

  private void addOnlyModelWithAllotment(PointsModel pointsModel, SpendingModel spendingModel) {
    if (spendingModel.getAllotment() > 0) {
      pointsModel.addToBonusOverview(spendingModel);
    }
  }

  private void initializeExperiencePoints(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AbilitiesModel abilities = AbilitiesModelFetcher.fetch(hero);
    AbilityExperienceData experienceData = new AbilityExperienceData(template);
    AbilityExperienceCalculator calculator = new AbilityExperienceCalculator(experienceData);
    pointsModel.addToExperienceOverview(new AbilityExperienceModel(abilities, calculator));
  }

  private AbilityCostCalculatorImpl createCalculator(Hero hero) {
    AbilityCreationData creationData = getCreationData();
    return new AbilityCostCalculatorImpl(AbilitiesModelFetcher.fetch(hero), creationData);
  }

  private AbilityCreationData getCreationData() {
    return new AbilityCreationData(template);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
