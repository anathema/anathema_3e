package net.sf.anathema.platform.fx.menu.exit;

import net.sf.anathema.library.interaction.model.Command;

public class AnathemaExitAction implements Command {

  @Override
  public void execute() {
    System.exit(0);
  }
}