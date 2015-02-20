package net.sf.anathema.equipment.editor.presenter.tools;

import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.stats.view.StatsEditViewFactory;
import net.sf.anathema.equipment.editor.stats.view.StatsEditor;
import net.sf.anathema.equipment.editor.view.ToolListView;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.taskbar.BasicUi;

public class EditStatsTool {
  private final StatsEditor statsEditor;
  private final Resources resources;
  private final StatsEditModel editModel;
  private final StatsEditViewFactory view;

  public EditStatsTool(Resources resources, StatsEditModel editModel, StatsEditor statsEditor,
                   StatsEditViewFactory view) {
    this.resources = resources;
    this.editModel = editModel;
    this.statsEditor = statsEditor;
    this.view = view;
  }

  public void addToolTo(final ToolListView<IEquipmentStats> statsListView) {
    final Tool tool = statsListView.addTool();
    tool.setIcon(new BasicUi().getEditIconPath());
    tool.setTooltip(resources.getString("Equipment.Creation.Stats.EditActionTooltip"));
    tool.setCommand(new EditStatsCommand(statsEditor, editModel, resources, view));
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