package net.sf.anathema.points.model.xp;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;

public interface ExperiencePointEntry {

  int getExperiencePoints();

  void setExperiencePoints(int points);

  ITextualDescription getTextualDescription();
  
  void initializePresentation(Resources resources);
  
  boolean allowModification();
  
  void rollBack();
}