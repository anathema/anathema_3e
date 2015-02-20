package net.sf.anathema.equipment.editor.stats.view;

import net.sf.anathema.equipment.editor.presenter.EquipmentStatsDialog;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.collection.Closure;
import net.sf.anathema.library.resources.Resources;

public interface StatsEditor {
  void editStats(Resources resources, IEquipmentStatisticsCreationModel model, EquipmentStatsDialog view);

  void whenChangesAreConfirmed(Closure<IEquipmentStats> action);
}