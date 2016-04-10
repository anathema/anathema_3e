package net.sf.anathema.points.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.library.text.SimpleTextualDescription;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePointType;

public class ManualExperiencePointEntry implements ExperiencePointEntry {

  private final ITextualDescription description = new SimpleTextualDescription("");
  private Map<ExperiencePointType, Integer> experiencePoints = new HashMap<>();
  
  @Override
	public Map<ExperiencePointType, Integer> getExperiencePointsCosted() {
  	HashMap<ExperiencePointType, Integer> map = new HashMap<>();
  	for (Entry<ExperiencePointType, Integer> entry : map.entrySet()) {
  		if (entry.getValue() < 0) {
  			map.put(entry.getKey(), entry.getValue());
  		}
  	}
		return map;
	}
  
  @Override
	public Map<ExperiencePointType, Integer> getExperiencePointsAwarded() {
  	HashMap<ExperiencePointType, Integer> map = new HashMap<>();
  	for (Entry<ExperiencePointType, Integer> entry : map.entrySet()) {
  		if (entry.getValue() > 0) {
  			map.put(entry.getKey(), entry.getValue());
  		}
  	}
		return map;
	}

	@Override
	public void setExperiencePoints(ExperiencePointType type, int points) {
		experiencePoints.put(type, points);
	}

	@Override
	public void setExperiencePoints(Map<ExperiencePointType, Integer> points) {
		experiencePoints = points;
	}

  @Override
  public ITextualDescription getTextualDescription() {
    return description;
  }
  
  @Override
	public void initializePresentation(Resources resources) {
    // nothing to do
  }

	@Override
	public boolean allowModification() {
		return true;
	}

	@Override
	public void rollBack() {
		// nothing to do
	}
}