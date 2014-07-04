package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.resources.Resources;

import java.util.List;
import java.util.stream.Collectors;

public class AddNewStats {
  protected final Resources resources;
  protected final StatsEditModel editModel;
  protected final EquipmentStatsFactory statsFactory;

  public AddNewStats(Resources resources, StatsEditModel editModel,
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
    controlAvailability(newStatsConfiguration, newTool);
    editModel.addCompositionChangeListener(() -> controlAvailability(newStatsConfiguration, newTool));
  }

  private void controlAvailability(NewStatsConfiguration newStatsConfiguration, Tool newTool) {
    if (statsFactory.canHaveThisKindOfStats(newStatsConfiguration.getType(), editModel.getMaterialComposition())) {
      newTool.enable();
    } else {
      newTool.disable();
    }
  }
}
