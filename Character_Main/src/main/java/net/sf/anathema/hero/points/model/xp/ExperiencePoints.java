package net.sf.anathema.hero.points.model.xp;

public interface ExperiencePoints {

  ExperiencePointEntry[] getAllEntries();

  ExperiencePointEntry addEntry();

  void removeEntry();

  int getTotalExperiencePoints();

  void addExperiencePointConfigurationListener(ExperiencePointsListener listener);

  void addEntrySelectionListener(ExperienceSelectionListener listener);

  int getExtraSpendings();

  void selectForChange(ExperiencePointEntry entry);

  void updateCurrentSelection(String description, int points);

  ExperiencePointEntry getCurrentSelection();
}