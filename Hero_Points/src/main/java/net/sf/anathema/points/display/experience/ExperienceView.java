package net.sf.anathema.points.display.experience;

import net.sf.anathema.hero.points.advance.experience.ExperiencePointEntry;
import net.sf.anathema.hero.points.advance.experience.ExperienceSelectionListener;
import net.sf.anathema.interaction.Tool;

public interface ExperienceView {

  void initGui(ExperienceViewProperties properties);

  Tool addTool();

  void addSelectionListener(ExperienceSelectionListener listener);

  void addUpdateListener(ExperienceUpdateListener experienceUpdateListener);

  void setEntries(ExperiencePointEntry... allEntries);

  void setTotalValueLabel(int overallExperiencePoints);

  void setSelection(ExperiencePointEntry entry);
}