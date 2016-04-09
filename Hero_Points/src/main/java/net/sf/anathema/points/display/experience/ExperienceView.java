package net.sf.anathema.points.display.experience;

import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperienceSelectionListener;

import java.util.Collection;

public interface ExperienceView {

  void initGui(ExperienceViewProperties properties, Resources resources);

  Tool addTool();

  void addSelectionListener(ExperienceSelectionListener listener);

  void addUpdateListener(ExperienceUpdateListener experienceUpdateListener);

  void setEntries(Collection<ExperiencePointEntry> allEntries);

  void setTotalValueLabel(int overallExperiencePoints);

  void setSelection(ExperiencePointEntry entry);
}