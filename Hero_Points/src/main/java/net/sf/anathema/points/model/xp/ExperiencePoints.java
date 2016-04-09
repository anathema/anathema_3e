package net.sf.anathema.points.model.xp;

import java.util.Collection;

public interface ExperiencePoints {

  Collection<ExperiencePointEntry> getAllEntries();
  
  ExperiencePointEntry addEntry(ExperiencePointEntry entry);

  ExperiencePointEntry addEntry();

  void removeEntry();
  
  void rollBackEntry();

  int getTotalExperiencePoints();

  void addExperiencePointConfigurationListener(ExperiencePointsListener listener);

  void addEntrySelectionListener(ExperienceSelectionListener listener);

  int getExtraSpendings();

  void selectForChange(ExperiencePointEntry entry);

  void updateCurrentSelection(String description, int points);

  ExperiencePointEntry getCurrentSelection();
}