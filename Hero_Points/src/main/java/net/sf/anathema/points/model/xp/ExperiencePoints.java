package net.sf.anathema.points.model.xp;

import java.util.Collection;
import java.util.Map;

public interface ExperiencePoints {

	// Experience types
	
	Collection<ExperiencePointType> getSupportedExperiencePointTypes();
	
	void addAdditionalSupportedExperiencePointType(ExperiencePointType type);
	
	// Entries
	
  Collection<ExperiencePointEntry> getAllEntries();
  
  ExperiencePointEntry addCharge(ExperiencePointCharge charge);

  ExperiencePointEntry addEntry();

  void removeEntry();
  
  void rollBackEntry();
  
  // Experience balances

  Map<ExperiencePointType, Integer> getTotalExperiencePoints();
  
  Map<ExperiencePointType, Integer> getSpentExperiencePoints();
  
  Map<ExperiencePointType, Integer> getAvaliableExperiencePoints();
  
  // Misc

  void addExperiencePointConfigurationListener(ExperiencePointsListener listener);

  void addEntrySelectionListener(ExperienceSelectionListener listener);

  void selectForChange(ExperiencePointEntry entry);

  void updateCurrentSelection(String description, Map<ExperiencePointType, Integer> points);

  ExperiencePointEntry getCurrentSelection();
}