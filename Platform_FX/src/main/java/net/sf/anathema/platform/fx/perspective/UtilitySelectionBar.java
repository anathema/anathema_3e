package net.sf.anathema.platform.fx.perspective;

import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;

import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.fx.tool.FxToggleTool;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.interaction.model.Tool;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.utility.UtilityToggle;

public class UtilitySelectionBar {
  private final ToolBar toolbar = new ToolBar();
  private final ToggleGroup buttonGroup = new ToggleGroup();
  private final PerspectiveStack perspectiveStack;

  public UtilitySelectionBar(PerspectiveStack perspectiveStack) {
    this.perspectiveStack = perspectiveStack;
  }

  public void addPerspective(UtilityPerspective perspective, Resources resources) {
    FxToggleTool tool = FxToggleTool.create();
    Command command = createCommand(perspective, tool);
    tool.setCommand(command);
    UtilityToggle toggle = new UtilityPerspectiveToggle(tool, resources);
    perspective.configureToggle(toggle);
    toolbar.getItems().add(tool.getNode());
    tool.registerWithGroup(buttonGroup);
  }

  private Command createCommand(UtilityPerspective perspective, FxToggleTool tool) {
      return () -> {
        perspectiveStack.show(perspective);
        tool.select();
      };
  }

  public Node getContent() {
    return toolbar;
  }

  public void selectFirstButton() {
    perspectiveStack.showFirst();
  }
  
  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForToolbar();
    toolbar.getItems().add(tool.getNode());
    return tool;
  }
}