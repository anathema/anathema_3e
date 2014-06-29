package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.abilities.advance.creation.*;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceCalculator;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceData;
import net.sf.anathema.hero.abilities.advance.experience.AbilityExperienceModel;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplate;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

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
    AbilitiesModel abilities = AbilityModelFetcher.fetch(hero);
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addBonusCategory(new WeightedCategory(200, "Abilities"));
    addOnlyModelWithAllotment(pointsModel, new DefaultAbilityBonusModel(abilityCalculator, getCreationData()));
    addOnlyModelWithAllotment(pointsModel, new FavoredAbilityBonusModel(abilityCalculator, getCreationData()));
    addOnlyModelWithAllotment(pointsModel, new FavoredAbilityPickModel(abilityCalculator, abilities.getFavoredCount()));
  }

  private void addOnlyModelWithAllotment(PointsModel pointsModel, SpendingModel spendingModel) {
    if (spendingModel.getAllotment() > 0) {
      pointsModel.addToBonusOverview(spendingModel);
    }
  }

  private void initializeExperiencePoints(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AbilitiesModel abilities = AbilityModelFetcher.fetch(hero);
    AbilityExperienceData experienceData = new AbilityExperienceData(template);
    AbilityExperienceCalculator calculator = new AbilityExperienceCalculator(experienceData);
    pointsModel.addToExperienceOverview(new AbilityExperienceModel(abilities, calculator));
  }

  private AbilityCostCalculatorImpl createCalculator(Hero hero) {
    AbilityCreationData creationData = getCreationData();
    return new AbilityCostCalculatorImpl(AbilityModelFetcher.fetch(hero), creationData);
  }

  private AbilityCreationData getCreationData() {
    return new AbilityCreationData(template);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}
