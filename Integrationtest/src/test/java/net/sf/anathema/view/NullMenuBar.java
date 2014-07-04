package net.sf.anathema.view;

import net.sf.anathema.platform.menu.IMenu;
import net.sf.anathema.platform.menu.MenuBar;

public class NullMenuBar implements MenuBar {
  @Override
  public IMenu getMainMenu() {
    return new NullMenu();
  }

  @Override
  public IMenu getHelpMenu() {
    return new NullMenu();
  }
}