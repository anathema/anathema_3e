package net.sf.anathema.equipment.editor.presenter.tools;

import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.view.ToolListView;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.taskbar.BasicUi;

public class RemoveStatsTool {

  private final Resources resources;
  private final StatsEditModel editModel;

  public RemoveStatsTool(Resources resources, StatsEditModel editModel) {
    this.resources = resources;
    this.editModel = editModel;
  }

  public void addToolTo(final ToolListView<IEquipmentStats> statsListView) {
    final Tool tool = statsListView.addTool();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setTooltip(resources.getString("Equipment.Stats.Action.Remove.Tooltip"));
    tool.setCommand(editModel::removeSelectedStatistics);
    editModel.whenSelectedStatsChanges(() -> updateEnabled(tool));
    updateEnabled(tool);
  }

  private void updateEnabled(Tool tool) {
    if (editModel.hasSelectedStats()) {
      tool.enable();
    } else {
      tool.disable();
    }
  }
}