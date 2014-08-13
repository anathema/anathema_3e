package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

public class MeritFreePointModel extends AbstractSpendingModel {

	private final MeritsModel model;
	
	public MeritFreePointModel(String id, MeritsModel merits) {
		super(id, id);
		this.model = merits;
	}

	@Override
	public int getSpentBonusPoints() {
		return 0;
	}

	@Override
	public int getAllotment() {
		return MeritsBonusPointsModel.MERIT_POINTS;
	}

	@Override
	public Integer getValue() {
		int totalMeritDots = 0;
		for (Merit merit : model.getMerits()) {
			totalMeritDots += merit.getCurrentValue();
		}
		return Math.min(MeritsBonusPointsModel.MERIT_POINTS, totalMeritDots);
	}

}
