package net.sf.anathema.equipment.editor.stats.view.fx;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import net.sf.anathema.equipment.editor.presenter.EquipmentStatsDialog;
import net.sf.anathema.equipment.editor.presenter.EquipmentStatsView;
import net.sf.anathema.equipment.editor.stats.presenter.dialog.OperationResultHandler;
import net.sf.anathema.equipment.editor.stats.presenter.dialog.StaticOperationResult;
import net.sf.anathema.library.message.Message;
import net.sf.anathema.platform.fx.environment.DialogFactory;

import java.util.Optional;

public class FxEditStatsDialog implements EquipmentStatsDialog {
  private final FxEquipmentStatsView view = new FxEquipmentStatsView();
  private Dialog<ButtonType> dialog;
  private String title;
  private String messageText;

  public FxEditStatsDialog(DialogFactory dialogFactory) {
    this.dialog = dialogFactory.createDialog(title);
    dialog.getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
  }

  public void setCanFinish() {
    Node button = dialog.getDialogPane().lookupButton(ButtonType.OK);
    button.setDisable(false);
  }

  public void setCannotFinish() {
    Node button = dialog.getDialogPane().lookupButton(ButtonType.OK);
    button.setDisable(true);
  }

  @Override
  public void setMessage(Message message) {
    this.messageText = message.getText();
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void show(OperationResultHandler handler) {
    dialog.setHeaderText(messageText);
    dialog.getDialogPane().setContent(view.getNode());
    Optional<ButtonType> result = dialog.showAndWait();
    dialog.hide();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      handler.handleOperationResult(StaticOperationResult.Confirmed());
    }
    else {
      handler.handleOperationResult(StaticOperationResult.Canceled());
    }
  }

  @Override
  public EquipmentStatsView getEquipmentStatsView() {
    return view;
  }
}
