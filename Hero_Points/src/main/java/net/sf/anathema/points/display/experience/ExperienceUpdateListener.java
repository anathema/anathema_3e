package net.sf.anathema.points.display.experience;

import java.util.Map;

import net.sf.anathema.points.model.xp.ExperiencePointType;

public interface ExperienceUpdateListener {
  void update(Map<ExperiencePointType, Integer> points, String description);
}