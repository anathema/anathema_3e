package net.sf.anathema.library.interaction.model;

import net.sf.anathema.library.view.Style;

public interface ToggleTool extends Toggle, Tool {
  void setStyle(Style style);
}
