package net.sf.anathema.points.model;

import net.sf.anathema.points.model.xp.ExperiencePointEntry;
import net.sf.anathema.points.model.xp.ExperiencePoints;
import net.sf.anathema.points.model.xp.ExperiencePointsListener;
import net.sf.anathema.points.model.xp.ExperienceSelectionListener;

import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultExperiencePointConfiguration implements ExperiencePoints {

  public static final ExperiencePointEntry NO_ENTRY = null;
  private ExperiencePointEntry currentlySelectedEntry = NO_ENTRY;
  private final List<ExperiencePointEntry> entries = new ArrayList<>();
  private final Announcer<ExperiencePointsListener> control = Announcer.to(ExperiencePointsListener.class);
  private final Announcer<ExperienceSelectionListener> selectionAnnouncer = Announcer.to(ExperienceSelectionListener.class);

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
  public ExperiencePointEntry addEntry(ExperiencePointEntry entry) {
	entries.add(entry);
	fireChangeEvent();
	return entry;
  }

  private ExperiencePointEntry addEntryWithoutEvent() {
    ExperiencePointEntry newEntry = new DefaultExperiencePointEntry();
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
  public int getTotalExperiencePoints() {
    int sum = 0;
    for (ExperiencePointEntry entry : getAllEntries()) {
      if (entry.getExperiencePoints() > 0) {
        sum += entry.getExperiencePoints();
      }
    }
    return sum;
  }

  @Override
  public int getExtraSpendings() {
    int sum = 0;
    for (ExperiencePointEntry entry : getAllEntries()) {
      if (entry.getExperiencePoints() < 0) {
        sum -= entry.getExperiencePoints();
      }
    }
    return sum;
  }

  @Override
  public void selectForChange(ExperiencePointEntry entry) {
    this.currentlySelectedEntry = entry;
    selectionAnnouncer.announce().selectionChanged(entry);
  }

  @Override
  public void updateCurrentSelection(String description, int points) {
    currentlySelectedEntry.getTextualDescription().setText(description);
    currentlySelectedEntry.setExperiencePoints(points);
    fireChangeEvent();
  }

  @Override
  public ExperiencePointEntry getCurrentSelection() {
    return currentlySelectedEntry;
  }
}