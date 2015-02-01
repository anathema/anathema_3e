package net.sf.anathema.platform.fx.perspective;

import net.sf.anathema.library.interaction.model.Tool;

public interface Stance extends Perspective {

  Tool createLeaveTool();

  void configureEnterTool(Tool tool);
}
