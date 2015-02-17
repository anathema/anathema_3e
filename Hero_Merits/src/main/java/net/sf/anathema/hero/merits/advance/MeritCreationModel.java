package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.merits.model.MeritsModel;
import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

public class MeritCreationModel extends AbstractSpendingModel {

	private final MeritsModel model;
	private final MeritCreationData creation;
	
	public MeritCreationModel(String id, MeritsModel merits, MeritCreationData creation) {
		super(id, id);
		this.model = merits;
		this.creation = creation;
	}

	@Override
	public int getSpentBonusPoints() {
		int totalMeritDots = 0;
		for (Merit merit : model.getMerits()) {
			totalMeritDots += merit.getCurrentValue();
		}
		return creation.getBonusPointCost() * 
				Math.max(totalMeritDots - creation.getFreebiePoints(), 0);
	}

	@Override
	public int getAllotment() {
		return creation.getFreebiePoints();
	}

	@Override
	public Integer getValue() {
		int totalMeritDots = 0;
		for (Merit merit : model.getMerits()) {
			totalMeritDots += merit.getCurrentValue();
		}
		return Math.min(creation.getFreebiePoints(), totalMeritDots);
	}
}