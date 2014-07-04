package net.sf.anathema.library.interaction.view;

import net.sf.anathema.library.interaction.model.Tool;

public interface MenuTool extends Tool {

  void clearMenu();

  Tool addMenuEntry();
}