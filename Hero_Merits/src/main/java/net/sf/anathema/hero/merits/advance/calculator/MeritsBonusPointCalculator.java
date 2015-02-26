package net.sf.anathema.hero.merits.advance.calculator;

import net.sf.anathema.hero.merits.advance.MeritCreationData;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.points.model.BonusPointCalculator;

public class MeritsBonusPointCalculator implements BonusPointCalculator {
	private MeritsModel model;
	private MeritCreationData creation;
	
	public MeritsBonusPointCalculator(MeritsModel model, MeritCreationData creation) {
		this.model = model;
		this.creation = creation;
	}
	
	@Override
	public void recalculate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBonusPointCost() {
		int totalMeritDots = 0;
		for (Merit merit : model.getKnownTraits()) {
			totalMeritDots += merit.getCurrentValue();
		}
		return creation.getBonusPointCost() *
				Math.max(0, totalMeritDots - creation.getFreebiePoints());
	}

	@Override
	public int getBonusPointsGranted() {
		return 0;
	}
}
