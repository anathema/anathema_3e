package net.sf.anathema.equipment.editor.presenter.tools;

import java.util.List;
import java.util.stream.Collectors;

import net.sf.anathema.equipment.editor.model.EquipmentStatsFactory;
import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.stats.presenter.NewStatsConfiguration;
import net.sf.anathema.equipment.editor.view.ToolListView;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

public class AddNewStatsTool {
  protected final Resources resources;
  protected final StatsEditModel editModel;
  protected final EquipmentStatsFactory statsFactory;

  public AddNewStatsTool(Resources resources, StatsEditModel editModel,
                     EquipmentStatsFactory statsFactory) {
    this.editModel = editModel;
    this.statsFactory = statsFactory;
    this.resources = resources;
  }

  public void addTool(final NewStatsConfiguration newStatsConfiguration, ToolListView<IEquipmentStats> statsListView) {
    final Tool newTool = statsListView.addTool();
    newTool.setTooltip(resources.getString(newStatsConfiguration.getTooltipKey()));
    newTool.setIcon(newStatsConfiguration.getIconPath());
    newTool.setOverlay(new RelativePath("icons/ButtonPlus16.png"));
    newTool.setCommand(() -> {
      List<String> definedNames = editModel.getStats().stream().map(stats -> stats.getName().getId()).collect(Collectors.toList());
      String nameProposal = resources.getString(newStatsConfiguration.getNameKey());
      IEquipmentStats equipmentStats = statsFactory.createNewStats(definedNames, nameProposal, newStatsConfiguration.getType());
      editModel.addStatistics(equipmentStats);
    });
  }
}
