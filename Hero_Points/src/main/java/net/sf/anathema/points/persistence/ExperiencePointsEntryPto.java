package net.sf.anathema.points.persistence;

import java.util.Map;

import net.sf.anathema.points.model.xp.ExperiencePointType;

public class ExperiencePointsEntryPto {
  public String description;
  public Map<ExperiencePointType, Integer> points;
}
