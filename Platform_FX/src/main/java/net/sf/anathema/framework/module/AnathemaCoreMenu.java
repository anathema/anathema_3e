package net.sf.anathema.framework.module;

import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.menu.MenuBar;
import net.sf.anathema.platform.menu.MenuEntry;

import java.util.Collection;

public class AnathemaCoreMenu {

  public void add(Environment environment, UiEnvironment uiEnvironment, ApplicationModel model, MenuBar menubar) {
    Collection<MenuEntry> collection = environment.instantiateOrdered(RegisteredMenuEntry.class, environment, uiEnvironment, model);
    for (MenuEntry menuEntry : collection) {
      menuEntry.addTo(menubar);
    }
  }
}