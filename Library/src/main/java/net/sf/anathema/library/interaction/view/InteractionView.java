package net.sf.anathema.library.interaction.view;

import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;

public interface InteractionView {

  Tool addTool();

  ToggleTool addToggleTool();

  MenuTool addMenuTool();
}