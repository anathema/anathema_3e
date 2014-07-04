package net.sf.anathema.view;

import net.sf.anathema.platform.frame.ApplicationFrame;
import net.sf.anathema.platform.frame.ApplicationFrameView;
import net.sf.anathema.platform.menu.MenuBar;
import net.sf.anathema.platform.messaging.StatusBar;

public class NullMainView implements ApplicationFrameView {

  @Override
  public ApplicationFrame getWindow() {
    return new NullWindow();
  }

  @Override
  public MenuBar getMenuBar() {
    return new NullMenuBar();
  }

  @Override
  public StatusBar getStatusBar() {
    return new NullStatusBar();
  }

  @Override
  public void show() {
    //nothing to do
  }
}