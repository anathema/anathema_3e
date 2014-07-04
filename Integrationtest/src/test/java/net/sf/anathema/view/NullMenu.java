package net.sf.anathema.view;

import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.platform.menu.IMenu;

public class NullMenu implements IMenu {
  @Override
  public void addMenuItem(Command action, String label) {
    //nothing to do
  }

}