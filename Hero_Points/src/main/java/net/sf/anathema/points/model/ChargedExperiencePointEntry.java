package net.sf.anathema.points.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.points.model.xp.ExperiencePointCharge;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePointType;

public class ChargedExperiencePointEntry implements ExperiencePointEntry {

	private ExperiencePointCharge charge;
	private Map<ExperiencePointType, Integer> amounts;
	private ITextualDescription description;
	
	public ChargedExperiencePointEntry(ExperiencePointCharge charge, Map<ExperiencePointType, Integer> amounts) {
		this.charge = charge;
		this.amounts = amounts;
	}
	
	@Override
	public Map<ExperiencePointType, Integer> getExperiencePointsCosted() {
		return amounts;
	}
	
	@Override
	public Map<ExperiencePointType, Integer> getExperiencePointsAwarded() {
		return new HashMap<>();
	}

	@Override
	public void setExperiencePoints(ExperiencePointType type, int points) {
		amounts.put(type, points);
	}
	
	@Override
	public void setExperiencePoints(Map<ExperiencePointType, Integer> points) {
		amounts = points;
	}

	@Override
	public ITextualDescription getTextualDescription() {
		return description;
	}

	@Override
	public void initializePresentation(Resources resources) {
		description = charge.getTextualDescription(resources);
	}

	@Override
	public boolean allowModification() {
		return false;
	}

	@Override
	public void rollBack() {
		charge.rollBack();
	}
}
