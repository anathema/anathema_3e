package net.sf.anathema.platform.fx.menu.updatecheck;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.UiEnvironment;
import net.sf.anathema.platform.fx.menu.RegisteredMenuEntry;
import net.sf.anathema.platform.menu.MenuBar;
import net.sf.anathema.platform.menu.MenuEntry;

@RegisteredMenuEntry
@Weight(weight = 30)
public class UpdateMenuEntry implements MenuEntry {

  private final Environment environment;
  private final UiEnvironment uiEnvironment;

  @SuppressWarnings("UnusedParameters")
  public UpdateMenuEntry(Environment environment, UiEnvironment uiEnvironment, ApplicationModel model) {
    this.environment = environment;
    this.uiEnvironment = uiEnvironment;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new UpdateAction(environment,uiEnvironment);
    String name = environment.getString("Help.UpdateCheck.Title") + "\u2026";
    menu.getHelpMenu().addMenuItem(action, name);
  }
}