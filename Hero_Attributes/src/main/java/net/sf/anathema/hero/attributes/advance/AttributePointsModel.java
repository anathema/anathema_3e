package net.sf.anathema.hero.attributes.advance;

import net.sf.anathema.hero.attributes.advance.creation.*;
import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.template.points.IAttributeCreationPoints;
import net.sf.anathema.hero.attributes.advance.experience.AttributesExperienceCalculator;
import net.sf.anathema.hero.attributes.advance.experience.AttributesExperienceModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
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

public class AttributePointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("AttributePoints");
  private AttributePointsTemplate template;

  public AttributePointsModel(AttributePointsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    initializeBonusPoints(hero);
    initializeExperience(hero);
  }
  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  private void initializeBonusPoints(Hero hero) {
    AttributeGroupCostsImpl calculator = createCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private void initBonusCalculation(Hero hero, AttributeGroupCostsImpl calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, AttributeGroupCosts calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(100, "Attributes"));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Primary));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Secondary));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Tertiary));
  }

  public SpendingModel createOverviewModel(AttributeGroupCosts calculator, AttributeGroupPriority priority) {
    return new AttributeBonusModel(calculator, priority);
  }

  private AttributeGroupCostsImpl createCalculator(Hero hero) {
    IAttributeCreationPoints creationPoints = hero.getTemplate().getCreationPoints().getAttributeCreationPoints();
    AttributeModel attributes = AttributesModelFetcher.fetch(hero);
    return new AttributeGroupCostsImpl(attributes, creationPoints, new AttributeBonusPointTemplate());
  }

  private void initializeExperience(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AttributeModel model = AttributesModelFetcher.fetch(hero);
    IExperiencePointCosts experienceCost = hero.getTemplate().getExperienceCost();
    AttributesExperienceCalculator calculator = new AttributesExperienceCalculator(experienceCost);
    pointsModel.addToExperienceOverview(new AttributesExperienceModel(model, calculator));
  }
}
