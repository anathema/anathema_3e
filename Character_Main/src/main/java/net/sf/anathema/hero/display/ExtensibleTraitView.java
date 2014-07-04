package net.sf.anathema.hero.display;

import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;

public interface ExtensibleTraitView {
  IntValueView getIntValueView();

  ToggleTool addToggleInFront();

  ToggleTool addToggleBehind();

  Tool addToolBehind();

  void remove();
}