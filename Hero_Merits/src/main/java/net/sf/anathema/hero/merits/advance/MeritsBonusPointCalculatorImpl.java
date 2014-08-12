package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;

public class MeritsBonusPointCalculatorImpl implements MeritsBonusPointCalculator {

	private MeritsModel model;
	
	public MeritsBonusPointCalculatorImpl(MeritsModel model) {
		this.model = model;
	}
	
	@Override
	public void recalculate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBonusPointCost() {
		int totalMeritDots = 0;
		for (Merit merit : model.getMerits()) {
			totalMeritDots += merit.getCurrentValue();
		}
		return MeritsBonusPointsModel.MERIT_BONUS_POINT_COST *
				Math.max(0, totalMeritDots - MeritsBonusPointsModel.MERIT_POINTS);
	}

	@Override
	public int getBonusPointsGranted() {
		return 0;
	}

}
