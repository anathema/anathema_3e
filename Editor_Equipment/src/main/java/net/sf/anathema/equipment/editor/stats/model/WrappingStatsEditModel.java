package net.sf.anathema.equipment.editor.stats.model;

import net.sf.anathema.equipment.editor.model.IEquipmentDatabaseManagement;
import net.sf.anathema.equipment.editor.model.IEquipmentTemplateEditModel;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.event.ChangeListener;

import org.jmock.example.announcer.Announcer;

import java.util.List;

public class WrappingStatsEditModel implements StatsEditModel {

  private static final IEquipmentStats NO_SELECTION = null;
  private final IEquipmentDatabaseManagement model;
  private final Announcer<ChangeListener> announcer = new Announcer<>(ChangeListener.class);
  private IEquipmentStats selectedStats;

  public WrappingStatsEditModel(IEquipmentDatabaseManagement model) {
    this.model = model;
  }

  @Override
  public void addStatsChangeListener(ChangeListener listener) {
    editModel().addStatsChangeListener(listener);
  }

  @Override
  public EquipmentStatsFactory getStatsCreationFactory() {
    return model.getStatsCreationFactory();
  }

  @Override
  public List<IEquipmentStats> getStats() {
    return editModel().getStats();
  }

  @Override
  public void addStatistics(IEquipmentStats equipmentStats) {
    editModel().addStatistics(equipmentStats);
  }

  @Override
  public void replaceSelectedStatistics(IEquipmentStats newStats) {
    editModel().replaceStatistics(this.selectedStats, newStats);
    selectStats(newStats);
  }

  @Override
  public void removeSelectedStatistics() {
    if(!hasSelectedStats()) {
      return;
    }
    editModel().removeStatistics(selectedStats);
    clearStatsSelection();
  }

  @Override
  public void selectStats(IEquipmentStats selected) {
    if (this.selectedStats == selected || selected == NO_SELECTION) {
      return;
    }
    this.selectedStats = selected;
    announcer.announce().changeOccurred();
  }

  @Override
  public IEquipmentStats getSelectedStats() {
    return selectedStats;
  }

  @Override
  public void whenSelectedStatsChanges(ChangeListener listener) {
    announcer.addListener(listener);
  }

  @Override
  public boolean hasSelectedStats() {
    return selectedStats != NO_SELECTION;
  }

  @Override
  public void clearStatsSelection() {
    this.selectedStats = NO_SELECTION;
    announcer.announce().changeOccurred();
  }

  private IEquipmentTemplateEditModel editModel() {
    return model.getTemplateEditModel();
  }
}