package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.view.Vetor;

import static java.text.MessageFormat.format;

public class RemoveEquipmentItem implements Command {
  private final EquipmentNavigation view;
  private final IEquipmentDatabaseManagement model;
  private final Resources resources;
  private final StatsEditModel editModel;

  public RemoveEquipmentItem(EquipmentNavigation view, IEquipmentDatabaseManagement model, Resources resources, StatsEditModel editModel) {
    this.view = view;
    this.model = model;
    this.resources = resources;
    this.editModel = editModel;
  }

  @Override
  public void execute() {
    String itemId = model.getTemplateEditModel().getEditTemplateId();
    String title = resources.getString("AnathemaCore.DialogTitle.ConfirmationDialog");
    String deleteNotification = resources.getString("Equipment.Creation.DeleteMessage.Text");
    String messageText = format("{0} - {1}", itemId, deleteNotification);
    Vetor vetor = view.createVetor(title, messageText);
    vetor.requestPermissionFor(() -> {
      model.getDatabase().deleteTemplate(view.getTemplateListView().getSelectedObject());
      model.getTemplateEditModel().setNewTemplate();
      editModel.clearStatsSelection();
      view.getTemplateListView().clearSelection();
    });
  }
}