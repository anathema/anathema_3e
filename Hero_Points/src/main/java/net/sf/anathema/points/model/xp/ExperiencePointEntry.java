package net.sf.anathema.points.model.xp;

import java.util.Map;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;

public interface ExperiencePointEntry {

  Map<ExperiencePointType, Integer> getExperiencePointsAwarded();
  
  Map<ExperiencePointType, Integer> getExperiencePointsCosted();

  void setExperiencePoints(ExperiencePointType type, int points);
  
  void setExperiencePoints(Map<ExperiencePointType, Integer> points);

  ITextualDescription getTextualDescription();
  
  void initializePresentation(Resources resources);
  
  boolean allowModification();
  
  void rollBack();
}