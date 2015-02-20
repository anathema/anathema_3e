package net.sf.anathema.equipment.editor.view;

import net.sf.anathema.equipment.editor.stats.view.StatsEditViewFactory;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;

public interface EquipmentDetails extends StatsEditViewFactory {

  ToolListView<IEquipmentStats> initStatsListView(String title, AgnosticUIConfiguration<IEquipmentStats> configuration);

  EquipmentDescriptionPanel addDescriptionPanel(String title);
}