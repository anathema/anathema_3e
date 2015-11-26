package net.sf.anathema.platform.fx.environment;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public interface DialogFactory {
  Dialog<ButtonType> createDialog(String title);
}