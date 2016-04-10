package net.sf.anathema.points.model;

import net.sf.anathema.points.model.xp.ExperiencePointCharge;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePointType;
import net.sf.anathema.points.model.xp.AbstractExperiencePointType;
import net.sf.anathema.points.model.xp.ExperiencePoints;
import net.sf.anathema.points.model.xp.ExperiencePointsListener;
import net.sf.anathema.points.model.xp.ExperienceSelectionListener;
import net.sf.anathema.points.model.xp.StandardExperiencePointType;

import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class DefaultExperiencePointConfiguration implements ExperiencePoints {

  public static final ExperiencePointEntry NO_ENTRY = null;
  private ExperiencePointEntry currentlySelectedEntry = NO_ENTRY;
  private final List<ExperiencePointType> supportedTypes = new ArrayList<>();
  private final List<ExperiencePointEntry> entries = new ArrayList<>();
  private final Announcer<ExperiencePointsListener> control = Announcer.to(ExperiencePointsListener.class);
  private final Announcer<ExperienceSelectionListener> selectionAnnouncer = Announcer.to(ExperienceSelectionListener.class);

  public DefaultExperiencePointConfiguration() {
  	supportedTypes.add(StandardExperiencePointType.Type);
  }
  
  public Collection<ExperiencePointType> getSupportedExperiencePointTypes() {
  	return supportedTypes;
  }
	
	public void addAdditionalSupportedExperiencePointType(ExperiencePointType type) {
		if (!supportedTypes.contains(type)) {
			supportedTypes.add(type);
		}
	}
  
  @Override
  public Collection<ExperiencePointEntry> getAllEntries() {
    return new ArrayList<>(entries);
  }

  @Override
  public ExperiencePointEntry addEntry() {
    ExperiencePointEntry newEntry = addEntryWithoutEvent();
    fireChangeEvent();
    return newEntry;
  }
  
  @Override
  public ExperiencePointEntry addCharge(ExperiencePointCharge charge) {
  	ExperiencePointEntry entry = handleCharge(charge);
  	entries.add(entry);
  	fireChangeEvent();
  	return entry;
  }

  private ExperiencePointEntry addEntryWithoutEvent() {
    ExperiencePointEntry newEntry = new ManualExperiencePointEntry();
    entries.add(newEntry);
    return newEntry;
  }

  @Override
  public void removeEntry() {
    removeEntry(currentlySelectedEntry);
  }
  
  @Override
  public void rollBackEntry() {
  	ExperiencePointEntry entry = entries.get(entries.size() - 1);
  	entry.rollBack();
    removeEntry(entry);
  }
  
  private void removeEntry(ExperiencePointEntry entry) {
  	entries.remove(entry);
    fireChangeEvent();
    selectForChange(NO_ENTRY);
  }

  private void fireChangeEvent() {
    control.announce().entriesAddedRemovedOrChanged();
  }

  @Override
  public void addExperiencePointConfigurationListener(ExperiencePointsListener listener) {
    control.addListener(listener);
  }

  @Override
  public void addEntrySelectionListener(ExperienceSelectionListener listener) {
    selectionAnnouncer.addListener(listener);
  }
  
  @Override
	public Map<ExperiencePointType, Integer> getTotalExperiencePoints() {
  	return computeExperienceSum(entry -> entry.getExperiencePointsAwarded());
	}
  
  @Override
	public Map<ExperiencePointType, Integer> getSpentExperiencePoints() {
  	return computeExperienceSum(entry -> entry.getExperiencePointsCosted());
	}
  
  @Override
	public Map<ExperiencePointType, Integer> getAvaliableExperiencePoints() {
  	Map<ExperiencePointType, Integer> totals = getTotalExperiencePoints();
  	Map<ExperiencePointType, Integer> spent = getSpentExperiencePoints();
  	for (ExperiencePointType type : totals.keySet()) {
  		int value = totals.get(type);
  		totals.put(type, Math.max(value - spent.get(type), 0));
  	}
  	return totals;
	}
  
  private Map<ExperiencePointType, Integer> computeExperienceSum(
  		Function<ExperiencePointEntry, Map<ExperiencePointType, Integer>> dataSetRetriever) {
  	Map<ExperiencePointType, Integer> totals = new HashMap<ExperiencePointType, Integer>();
  	for (ExperiencePointType type : supportedTypes) {
  		totals.put(type, 0);
  	}
  	
  	for (ExperiencePointEntry entry : getAllEntries()) {
  		Map<ExperiencePointType, Integer> dataSet = dataSetRetriever.apply(entry);
  		
      for (ExperiencePointType type : supportedTypes) {
      	if (!dataSet.containsKey(type)) {
      		continue;
      	}
      	int amount = dataSet.get(type);
      	if (amount > 0) {
      		int currentAmount = totals.get(type);
      		currentAmount += amount;
      		totals.put(type, currentAmount);
      	}
      }
    }
  	
  	return totals;
  }
  
  private ExperiencePointEntry handleCharge(ExperiencePointCharge charge) {
  	
  	int amountToSpend = charge.getExperiencePointCharge();
  	Map<ExperiencePointType, Integer> avaliableBudget = getAvaliableExperiencePoints();
  	Map<ExperiencePointType, Integer> entryExpenditure = new HashMap<ExperiencePointType, Integer>();
  	
  	// TODO: Types should be prioritized somehow
  	for (ExperiencePointType experienceType : supportedTypes) {
  		// we have spent all that we need to
  		if (amountToSpend <= 0)
  			break;
  		
  		// This type of XP cannot be spent on this expenditure
  		if (!experienceType.supportsExpenditureType(charge.getType())) {
  			continue;
  		}
  		
  		int typeCharge = Math.min(amountToSpend, avaliableBudget.get(experienceType));
  		if (typeCharge > 0) {
  			entryExpenditure.put(experienceType, typeCharge);
  			amountToSpend -= typeCharge;
  		}
  	}
  	
  	if (amountToSpend > 0) {
  		int standardAmount = entryExpenditure.containsKey(StandardExperiencePointType.Type) ?
    			entryExpenditure.get(StandardExperiencePointType.Type) : 0;
    	standardAmount += amountToSpend;
    	entryExpenditure.put(StandardExperiencePointType.Type, standardAmount);
  	}
  	
  	return new ChargedExperiencePointEntry(charge, entryExpenditure);
  }

  @Override
  public void selectForChange(ExperiencePointEntry entry) {
    this.currentlySelectedEntry = entry;
    selectionAnnouncer.announce().selectionChanged(entry);
  }

  @Override
  public void updateCurrentSelection(String description, Map<ExperiencePointType, Integer> points) {
    currentlySelectedEntry.getTextualDescription().setText(description);
    currentlySelectedEntry.setExperiencePoints(points);
    fireChangeEvent();
  }

  @Override
  public ExperiencePointEntry getCurrentSelection() {
    return currentlySelectedEntry;
  }
}