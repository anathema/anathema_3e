package net.sf.anathema.framework.presenter.action.exit;

import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.module.RegisteredMenuEntry;
import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.menu.MenuBar;
import net.sf.anathema.platform.menu.MenuEntry;

@RegisteredMenuEntry
@Weight(weight = 20)
public class ExitMenuEntry implements MenuEntry {

  private final Environment environment;

  @SuppressWarnings("UnusedParameters")
  public ExitMenuEntry(Environment environment, UiEnvironment uiEnvironment, ApplicationModel model) {
    this.environment = environment;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new AnathemaExitAction();
    String name = environment.getString("AnathemaCore.Tools.Exit.Name");
    menu.getMainMenu().addMenuItem(action, name);
  }
}
