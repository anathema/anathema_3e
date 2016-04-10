package net.sf.anathema.points.model.xp;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;

public interface ExperiencePointCharge {
	
	int getExperiencePointCharge();
	
	ExperienceExpenditureType getType();

  ITextualDescription getTextualDescription(Resources resources);
  
  void rollBack();
}
