package net.sf.anathema.platform.menu;

import net.sf.anathema.library.interaction.model.Command;

public interface IMenu {

  void addMenuItem(Command action, String label);
}