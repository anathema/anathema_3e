package net.sf.anathema.platform.fx.utility;

import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import net.sf.anathema.library.fx.tool.FxToggleTool;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.utility.UtilityToggle;

public class UtilitySelectionBar {
  private final ToolBar toolbar = new ToolBar();
  private final ToggleGroup buttonGroup = new ToggleGroup();
  private final UtilityStack utilityStack;

  public UtilitySelectionBar(UtilityStack utilityStack) {
    this.utilityStack = utilityStack;
  }

  public void addPerspective(UtilityPerspective perspective, Resources resources) {
    FxToggleTool tool = FxToggleTool.create();
    Command command = createCommand(perspective, tool);
    tool.setCommand(command);
    UtilityToggle toggle = new ToolPerspectiveToggle(tool, resources);
    perspective.configureToggle(toggle);
    toolbar.getItems().add(tool.getNode());
    tool.registerWithGroup(buttonGroup);
  }

  private Command createCommand(UtilityPerspective perspective, FxToggleTool tool) {
      return () -> {
        utilityStack.show(perspective);
        tool.select();
      };
  }

  public Node getContent() {
    return toolbar;
  }

  public void selectFirstButton() {
    utilityStack.showFirst();
  }
}