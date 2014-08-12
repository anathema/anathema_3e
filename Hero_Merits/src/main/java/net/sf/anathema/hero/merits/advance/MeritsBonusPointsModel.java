package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.environment.HeroEnvironment;
import net.sf.anathema.hero.individual.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.hero.merits.model.MeritsModelFetcher;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.identifier.SimpleIdentifier;
import net.sf.anathema.points.model.PointModelFetcher;
import net.sf.anathema.points.model.PointsModel;
import net.sf.anathema.points.model.overview.WeightedCategory;

public class MeritsBonusPointsModel implements HeroModel {

	public static final Identifier ID = new SimpleIdentifier("MeritPoints");
	// TODO: Character merits template

	public static final int MERIT_POINTS = 7;
	public static final int MERIT_BONUS_POINT_COST = 1;
	
	@Override
	public Identifier getId() {
		return ID;
	}
	
	@Override
	public void initialize(HeroEnvironment environment, Hero hero) {
		
		initializeBonusPoints(hero);
	}
	private void initializeBonusPoints(Hero hero) {
		MeritsBonusPointCalculatorImpl meritCalculator = createCalculator(hero);
		initializeBonusCalculator(hero, meritCalculator);
		initializeBonusOverview(hero);
	}
	
	private MeritsBonusPointCalculatorImpl createCalculator(Hero hero) {
	    // TODO: Character merit template
	    return new MeritsBonusPointCalculatorImpl(MeritsModelFetcher.fetch(hero));
	  }

	private void initializeBonusCalculator(Hero hero, MeritsBonusPointCalculatorImpl calculator) {
		PointsModel pointsModel = PointModelFetcher.fetch(hero);
		pointsModel.addBonusPointCalculator(calculator);
	}

	private void initializeBonusOverview(Hero hero) {
		MeritsModel merits = MeritsModelFetcher.fetch(hero);
		PointsModel pointsModel = PointModelFetcher.fetch(hero);
		pointsModel.addBonusCategory(new WeightedCategory(300, "Merits"));
		pointsModel.addToBonusOverview(new MeritFreePointModel("Merits", merits));

	}

	@Override
	public void initializeListening(ChangeAnnouncer announcer) {
		// nothing to do
	}
}
