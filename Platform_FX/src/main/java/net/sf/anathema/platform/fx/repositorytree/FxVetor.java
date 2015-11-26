package net.sf.anathema.platform.fx.repositorytree;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.view.Vetor;

import java.util.Optional;

public class FxVetor implements Vetor {
  private String title;
  private final String message;

  public FxVetor(String title, String message) {
    this.title = title;
    this.message = message;
  }

  @Override
  public void requestPermissionFor(Command command) {
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle(title);
    dialog.getDialogPane().setContentText(message);
    dialog.getDialogPane().getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
    Optional<ButtonType> result = dialog.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.YES) {
      command.execute();
    }
  }
}