package net.sf.anathema.equipment.editor.presenter.tools;

import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.stats.model.StatsToModel;
import net.sf.anathema.equipment.editor.stats.view.StatsEditViewFactory;
import net.sf.anathema.equipment.editor.stats.view.StatsEditor;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.collection.Closure;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.resources.Resources;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class EditStatsCommand implements Command {
  private final StatsEditor statsEditor;
  private final StatsEditModel editModel;
  private final Resources resources;
  private final StatsEditViewFactory view;

  public EditStatsCommand(StatsEditor statsEditor, StatsEditModel editModel, Resources resources,
                          StatsEditViewFactory view) {
    this.statsEditor = statsEditor;
    this.editModel = editModel;
    this.resources = resources;
    this.view = view;
  }

  @Override
  public void execute() {
    Collection<String> forbiddenNames = getNamesOfAllOtherStats();
    statsEditor.whenChangesAreConfirmed(new ReplaceStats());
    IEquipmentStats selectedStats = editModel.getSelectedStats();
    IEquipmentStatisticsCreationModel model = new StatsToModel().createModel(selectedStats);
    model.setForbiddenNames(forbiddenNames);
    statsEditor.editStats(resources, model, view.createEquipmentStatsDialog());
  }

  private Collection<String> getNamesOfAllOtherStats() {
    IEquipmentStats selectedStats = editModel.getSelectedStats();
    Stream<IEquipmentStats> allStats = editModel.getStats().stream();
    return allStats.filter(stats -> stats != selectedStats).map(stats -> stats.getName().getId()).collect(toList());
  }

  private class ReplaceStats implements Closure<IEquipmentStats> {

    @Override
    public void execute(IEquipmentStats newStats) {
      editModel.replaceSelectedStatistics(newStats);
    }
  }
}