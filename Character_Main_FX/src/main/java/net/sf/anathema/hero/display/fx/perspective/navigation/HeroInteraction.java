package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.library.interaction.view.MenuTool;

public class HeroInteraction implements InteractionView {
  private HBox tools;

  public HeroInteraction(HBox tools) {
    this.tools = tools;
  }

  @Override
  public Tool addTool() {
    HeroPoolTool tool = HeroPoolTool.createTool(new Button());
    tools.getChildren().add(tool.getNode());
    return tool;
  }

  @Override
  public ToggleTool addToggleTool() {
    throw new UnsupportedOperationException();
  }

  @Override
  public MenuTool addMenuTool() {
    throw new UnsupportedOperationException();
  }
}
