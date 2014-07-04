package net.sf.anathema.hero.attributes.advance;

import net.sf.anathema.hero.attributes.advance.creation.AttributeBonusModel;
import net.sf.anathema.hero.attributes.advance.creation.AttributeCreationData;
import net.sf.anathema.hero.attributes.advance.creation.AttributeCreationPointCalculator;
import net.sf.anathema.hero.attributes.advance.creation.AttributeGroupPoints;
import net.sf.anathema.hero.attributes.advance.experience.AttributesExperienceCalculator;
import net.sf.anathema.hero.attributes.advance.experience.AttributesExperienceData;
import net.sf.anathema.hero.attributes.advance.experience.AttributesExperienceModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.model.overview.SpendingModel;
import net.sf.anathema.points.model.overview.WeightedCategory;

public class AttributePointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("AttributePoints");
  private final AttributePointsTemplate template;

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
    AttributeCreationPointCalculator calculator = createCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private void initBonusCalculation(Hero hero, AttributeCreationPointCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, AttributeGroupPoints calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(100, "Attributes"));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Primary));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Secondary));
    PointModelFetcher.fetch(hero).addToBonusOverview(createOverviewModel(calculator, AttributeGroupPriority.Tertiary));
  }

  public SpendingModel createOverviewModel(AttributeGroupPoints calculator, AttributeGroupPriority priority) {
    return new AttributeBonusModel(calculator, priority);
  }

  private AttributeCreationData getCreationData() {
    return new AttributeCreationData(template);
  }


  private AttributeCreationPointCalculator createCalculator(Hero hero) {
    AttributeModel attributes = AttributesModelFetcher.fetch(hero);
    return new AttributeCreationPointCalculator(attributes, getCreationData());
  }

  private void initializeExperience(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    AttributeModel model = AttributesModelFetcher.fetch(hero);
    AttributesExperienceData experienceData = new AttributesExperienceData(template);
    AttributesExperienceCalculator calculator = new AttributesExperienceCalculator(experienceData);
    pointsModel.addToExperienceOverview(new AttributesExperienceModel(model, calculator));
  }
}
