package net.sf.anathema.hero.points.model.xp;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ExperiencePointEntry {

  int getExperiencePoints();

  void setExperiencePoints(int points);

  ITextualDescription getTextualDescription();
}