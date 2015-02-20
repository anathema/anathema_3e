package net.sf.anathema.equipment.editor.presenter.tools;

import net.sf.anathema.equipment.editor.model.IEquipmentDatabaseManagement;
import net.sf.anathema.equipment.editor.presenter.DiscardChangesVetor;
import net.sf.anathema.equipment.editor.stats.model.StatsEditModel;
import net.sf.anathema.equipment.editor.view.EquipmentNavigation;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.resources.Resources;

public class NewEquipmentItemAction implements Command {
  private final IEquipmentDatabaseManagement model;
  private final EquipmentNavigation view;
  private final Resources resources;
  private StatsEditModel editModel;

  public NewEquipmentItemAction(IEquipmentDatabaseManagement model, EquipmentNavigation view, Resources resources, StatsEditModel editModel) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.editModel = editModel;
  }

  @Override
  public void execute() {
    DiscardChangesVetor vetor = new DiscardChangesVetor(model, view, resources);
    vetor.requestPermissionFor(() -> {
      model.getTemplateEditModel().setNewTemplate();
      editModel.clearStatsSelection();
      view.getTemplateListView().clearSelection();
    });
  }
}