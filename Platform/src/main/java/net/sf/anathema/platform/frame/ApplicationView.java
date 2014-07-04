package net.sf.anathema.platform.frame;

import net.sf.anathema.platform.menu.MenuBar;
import net.sf.anathema.platform.messaging.StatusBar;

public interface ApplicationView {

  ApplicationFrame getWindow();

  MenuBar getMenuBar();

  StatusBar getStatusBar();
}