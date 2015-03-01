package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.merits.advance.calculator.MeritExperienceCalculator;
import net.sf.anathema.hero.merits.advance.calculator.MeritsBonusPointCalculator;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.hero.merits.template.MeritPointsTemplate;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.model.overview.WeightedCategory;

public class MeritPointsModel implements HeroModel {

	public static final Identifier ID = new SimpleIdentifier("MeritPoints");

	private final MeritPointsTemplate template;
	
	public MeritPointsModel(MeritPointsTemplate template) {
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
		MeritsBonusPointCalculator meritCalculator = createCalculator(hero);
		initializeBonusCalculator(hero, meritCalculator);
		initializeBonusOverview(hero, meritCalculator);
	}
	
	private MeritsBonusPointCalculator createCalculator(Hero hero) {
	    return new MeritsBonusPointCalculator(MeritsModelFetcher.fetch(hero), new MeritCreationData(template));
	  }

	private void initializeBonusCalculator(Hero hero, MeritsBonusPointCalculator calculator) {
		PointsModel pointsModel = PointModelFetcher.fetch(hero);
		pointsModel.addBonusPointCalculator(calculator);
	}

	private void initializeBonusOverview(Hero hero, MeritsBonusPointCalculator meritCalculator) {
		PointsModel pointsModel = PointModelFetcher.fetch(hero);
		pointsModel.addBonusCategory(new WeightedCategory(300, "Merits"));
		pointsModel.addToBonusOverview(new MeritCreationModel("Merits", new MeritCreationData(template), meritCalculator));
	}
	
	private void initializeExperiencePoints(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    MeritsModel merits = MeritsModelFetcher.fetch(hero);
    MeritExperienceData experienceData = new MeritExperienceData(template);
    MeritExperienceCalculator calculator = new MeritExperienceCalculator(experienceData);
    pointsModel.addToExperienceOverview(new MeritExperienceModel("Merits", merits, calculator));
  }

	@Override
	public void initializeListening(ChangeAnnouncer announcer) {
		// nothing to do
	}
}
