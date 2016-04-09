package net.sf.anathema.points.model;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.library.text.SimpleTextualDescription;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;

public class DefaultExperiencePointEntry implements ExperiencePointEntry {

  private final ITextualDescription description = new SimpleTextualDescription("");
  private int experiencePoints = 0;

  @Override
  public int getExperiencePoints() {
    return experiencePoints;
  }

  @Override
  public void setExperiencePoints(int value) {
    if (experiencePoints == value) {
      return;
    }
    this.experiencePoints = value;
  }

  @Override
  public ITextualDescription getTextualDescription() {
    return description;
  }

	@Override
	public boolean allowModification() {
		return true;
	}

	@Override
	public void initializePresentation(Resources resources) {
		// nothing to do
	}
}