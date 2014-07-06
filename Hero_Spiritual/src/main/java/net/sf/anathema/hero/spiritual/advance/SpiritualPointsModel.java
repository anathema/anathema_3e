package net.sf.anathema.hero.spiritual.advance;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.spiritual.advance.creation.DefaultSpiritualCreationData;
import net.sf.anathema.hero.spiritual.advance.creation.SpiritualBonusPointsCalculator;
import net.sf.anathema.hero.spiritual.advance.experience.EssenceExperienceModel;
import net.sf.anathema.hero.spiritual.advance.experience.SpiritualExperienceCalculator;
import net.sf.anathema.hero.spiritual.advance.experience.SpiritualExperienceData;
import net.sf.anathema.hero.spiritual.advance.experience.WillpowerExperienceModel;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModel;
import net.sf.anathema.hero.spiritual.model.traits.SpiritualTraitModelFetcher;
import net.sf.anathema.hero.spiritual.template.points.SpiritualPointsTemplate;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;

public class SpiritualPointsModel implements HeroModel {

  public static final SimpleIdentifier ID = new SimpleIdentifier("SpiritualPoints");
  private SpiritualPointsTemplate template;

  public SpiritualPointsModel(SpiritualPointsTemplate template) {
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
    // nothing to do, until bonus points are created the way, they should be
  }

  private void initializeBonusPoints(Hero hero) {
    SpiritualBonusPointsCalculator calculator = createBonusCalculator(hero);
    PointModelFetcher.fetch(hero).addBonusPointCalculator(calculator);
  }

  private SpiritualBonusPointsCalculator createBonusCalculator(Hero hero) {
    SpiritualTraitModel model = SpiritualTraitModelFetcher.fetch(hero);
    DefaultSpiritualCreationData creationData = new DefaultSpiritualCreationData(template);
    return new SpiritualBonusPointsCalculator(model, creationData);
  }

  private void initializeExperience(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    SpiritualTraitModel model = SpiritualTraitModelFetcher.fetch(hero);
    SpiritualExperienceData experienceData = new SpiritualExperienceData(template);
    SpiritualExperienceCalculator calculator = new SpiritualExperienceCalculator(experienceData);
    pointsModel.addToExperienceOverview(new EssenceExperienceModel(model, calculator));
    pointsModel.addToExperienceOverview(new WillpowerExperienceModel(model, calculator));
  }
}
