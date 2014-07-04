package net.sf.anathema.hero.charms.advance;

import net.sf.anathema.charm.data.martial.MartialArtsLevel;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.creation.DefaultMagicModel;
import net.sf.anathema.hero.charms.advance.creation.FavoredMagicModel;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostCalculator;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationData;
import net.sf.anathema.hero.charms.advance.experience.CharmExperienceCostCalculator;
import net.sf.anathema.hero.charms.advance.experience.CharmExperienceModel;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceData;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
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

public class MagicPointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("MagicPoints");
  private MagicPointsTemplate template;
  private MartialArtsLevel standardMartialArtsLevel;

  public MagicPointsModel(MagicPointsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.standardMartialArtsLevel = CharmsModelFetcher.fetch(hero).getStandardMartialArtsLevel();
    initializeBonusPoints(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do, until bonus points are created the way, they should be
  }

  public MagicExperienceData getExperienceCost() {
    return new MagicExperienceData(template, standardMartialArtsLevel);
  }

  private MagicCreationData getMagicCreationData() {
    return new MagicCreationData(template, standardMartialArtsLevel);
  }

  private void initializeBonusPoints(Hero hero) {
    initCreation(hero);
    initializeExperience(hero);
  }

  private void initializeExperience(Hero hero) {
    CharmExperienceCostCalculator calculator = createExperienceCalculator();
    initExperienceOverview(hero, calculator);
  }

  private void initCreation(Hero hero) {
    MagicCreationCostCalculator calculator = createBonusCalculator(hero);
    initBonusCalculation(hero, calculator);
    initBonusOverview(hero, calculator);
  }

  private void initBonusCalculation(Hero hero, MagicCreationCostCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private void initBonusOverview(Hero hero, MagicCreationCostCalculator calculator) {
    PointModelFetcher.fetch(hero).addBonusCategory(new WeightedCategory(400, "Charms"));
    PointModelFetcher.fetch(hero).addToBonusOverview(new DefaultMagicModel(calculator, getMagicCreationData()));
    SpendingModel favoredMagicModel = new FavoredMagicModel(calculator, getMagicCreationData());
    if (favoredMagicModel.getAllotment() > 0) {
      PointModelFetcher.fetch(hero).addToBonusOverview(favoredMagicModel);
    }
  }

  private MagicCreationCostCalculator createBonusCalculator(Hero hero) {
    CharmsModel model = CharmsModelFetcher.fetch(hero);
    return new MagicCreationCostCalculator(model.getMagicCostEvaluator(), getMagicCreationData(), new CostAnalyzerImpl(hero));
  }

  private CharmExperienceCostCalculator createExperienceCalculator() {
    return new CharmExperienceCostCalculator(getExperienceCost());
  }

  private void initExperienceOverview(Hero hero, CharmExperienceCostCalculator calculator) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    pointsModel.addToExperienceOverview(new CharmExperienceModel(calculator, hero));
  }
}
