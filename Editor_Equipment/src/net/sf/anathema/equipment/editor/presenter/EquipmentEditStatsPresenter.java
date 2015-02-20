package net.sf.anathema.equipment.editor.presenter;

import net.sf.anathema.equipment.editor.presenter.tools.AddNewStatsTool;
import net.sf.anathema.equipment.editor.presenter.tools.EditStatsTool;
import net.sf.anathema.equipment.editor.presenter.tools.RemoveStatsTool;
import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.stats.presenter.AgnosticStatsEditor;
import net.sf.anathema.equipment.editor.stats.presenter.ArmourStatsConfiguration;
import net.sf.anathema.equipment.editor.stats.presenter.ArtifactStatsConfiguration;
import net.sf.anathema.equipment.editor.stats.presenter.TraitModifierStatsConfiguration;
import net.sf.anathema.equipment.editor.stats.presenter.WeaponStatsConfiguration;
import net.sf.anathema.equipment.editor.view.EquipmentDetails;
import net.sf.anathema.equipment.editor.view.ToolListView;
import net.sf.anathema.equipment.presentation.EquipmentStatsUIConfiguration;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.library.resources.Resources;

public class EquipmentEditStatsPresenter {

  private final Resources resources;
  private final EquipmentDetails equipmentView;
  private final StatsEditModel model;

  public EquipmentEditStatsPresenter(Resources resources, StatsEditModel model, EquipmentDetails equipmentView) {
    this.resources = resources;
    this.model = model;
    this.equipmentView = equipmentView;
  }

  public void initPresentation() {
    String title = resources.getString("Equipment.Creation.Stats");
    ToolListView<IEquipmentStats> statsListView = equipmentView.initStatsListView(title,
            new EquipmentStatsUIConfiguration(resources));
    initListening(statsListView);
    initButtons(statsListView);
  }

  private void initListening(final ToolListView<IEquipmentStats> view) {
    model.addStatsChangeListener(() -> updateStatListContent(view));
    view.addListSelectionListener(model::selectStats);
  }

  private void initButtons(ToolListView<IEquipmentStats> statsListView) {
    AddNewStatsTool addNewStats = new AddNewStatsTool(resources, model, model.getStatsCreationFactory());
    addNewStats.addTool(new WeaponStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArmourStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArtifactStatsConfiguration(), statsListView);
    addNewStats.addTool(new TraitModifierStatsConfiguration(), statsListView);
    new EditStatsTool(resources, model, new AgnosticStatsEditor(), equipmentView).addToolTo(statsListView);
    new RemoveStatsTool(resources, model).addToolTo(statsListView);
  }

  private void updateStatListContent(ToolListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getStats());
  }
}