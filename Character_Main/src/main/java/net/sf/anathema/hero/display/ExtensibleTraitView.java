package net.sf.anathema.hero.display;

import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.view.IntValueView;

public interface ExtensibleTraitView {
  IntValueView getIntValueView();

  ToggleTool addToggleInFront();

  ToggleTool addToggleBehind();

  Tool addToolBehind();

  void remove();
}