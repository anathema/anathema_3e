package net.sf.anathema.platform.fx.menu.about;

import net.sf.anathema.library.initialization.Weight;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.platform.environment.Environment;
import net.sf.anathema.platform.frame.ApplicationModel;
import net.sf.anathema.platform.fx.environment.DialogFactory;
import net.sf.anathema.platform.fx.menu.RegisteredMenuEntry;
import net.sf.anathema.platform.menu.MenuBar;
import net.sf.anathema.platform.menu.MenuEntry;

@RegisteredMenuEntry
@Weight(weight = 40)
public class AboutMenuEntry implements MenuEntry {

  private final Environment environment;
  private final DialogFactory dialogFactory;

  @SuppressWarnings("UnusedParameters")
  public AboutMenuEntry(Environment environment, DialogFactory dialogFactory, ApplicationModel model) {
    this.environment = environment;
    this.dialogFactory = dialogFactory;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new AnathemaAboutAction(environment, dialogFactory);
    String name = environment.getString("Help.AboutDialog.Title");
    menu.getHelpMenu().addMenuItem(action, name);
  }
}
