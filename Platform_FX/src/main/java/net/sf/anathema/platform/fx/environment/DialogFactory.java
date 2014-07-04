package net.sf.anathema.platform.fx.environment;

import org.controlsfx.dialog.Dialog;

public interface DialogFactory {
  Dialog createDialog(String title);
}