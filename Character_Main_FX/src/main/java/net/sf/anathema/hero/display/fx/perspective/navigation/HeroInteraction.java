package net.sf.anathema.hero.display.fx.perspective.navigation;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.interaction.view.InteractionView;
import net.sf.anathema.library.interaction.view.MenuTool;

public class HeroInteraction implements InteractionView {
  private HBox tools;
  private ToggleGroup toggleGroup;

  public HeroInteraction(HBox tools, ToggleGroup toggleGroup) {
    this.tools = tools;
    this.toggleGroup = toggleGroup;
  }

  @Override
  public Tool addTool() {
    HeroPoolTool tool = HeroPoolTool.createTool(new Button());
    tools.getChildren().add(tool.getNode());
    return tool;
  }

  @Override
  public ToggleTool addToggleTool() {
    HeroPoolToggleTool tool = new HeroPoolToggleTool();
    tool.setToggleGroup(toggleGroup);
    tools.getChildren().add(tool.getNode());
    return tool;
  }

  @Override
  public MenuTool addMenuTool() {
    throw new UnsupportedOperationException();
  }
}
