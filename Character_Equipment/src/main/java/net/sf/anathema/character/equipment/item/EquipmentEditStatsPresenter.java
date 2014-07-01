package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.creation.presenter.AgnosticStatsEditor;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

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
    AddNewStats addNewStats = new AddNewStats(resources, model, model.getStatsCreationFactory());
    addNewStats.addTool(new WeaponStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArmourStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArtifactStatsConfiguration(), statsListView);
    addNewStats.addTool(new TraitModifierStatsConfiguration(), statsListView);
    new EditStats(resources, model, new AgnosticStatsEditor(), equipmentView).addToolTo(statsListView);
    new RemoveStats(resources, model).addToolTo(statsListView);
  }

  private void updateStatListContent(ToolListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getStats());
  }
}