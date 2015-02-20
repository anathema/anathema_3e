package net.sf.anathema.equipment.editor.presenter.tools;

import net.sf.anathema.equipment.editor.model.IEquipmentDatabaseManagement;
import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.view.EquipmentNavigation;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.taskbar.BasicUi;

public class NewEquipmentTemplateTool {

  private final Resources resources;
  private final IEquipmentDatabaseManagement model;
  private StatsEditModel editModel;

  public NewEquipmentTemplateTool(Resources resources, IEquipmentDatabaseManagement model, StatsEditModel editModel) {
    this.resources = resources;
    this.model = model;
    this.editModel = editModel;
  }

  public void addToolTo(EquipmentNavigation view) {
    Tool newTool = view.addEditTemplateTool();
    newTool.setIcon(new BasicUi().getNewIconPathForTaskbar());
    newTool.setTooltip(resources.getString("Equipment.Creation.Item.NewActionTooltip"));
    newTool.setCommand(new NewEquipmentItemAction(model, view, resources, editModel));
  }
}