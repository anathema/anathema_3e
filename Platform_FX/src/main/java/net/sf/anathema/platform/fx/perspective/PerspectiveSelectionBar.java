package net.sf.anathema.platform.fx.perspective;

import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import net.sf.anathema.library.fx.tool.FxToggleTool;
import net.sf.anathema.library.interaction.model.Command;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.perspective.PerspectiveToggle;

public class PerspectiveSelectionBar {
  private final ToolBar toolbar = new ToolBar();
  private final ToggleGroup buttonGroup = new ToggleGroup();
  private final PerspectiveStack perspectiveStack;

  public PerspectiveSelectionBar(PerspectiveStack perspectiveStack) {
    this.perspectiveStack = perspectiveStack;
  }

  public void addPerspective(Perspective perspective, Resources resources) {
    FxToggleTool tool = FxToggleTool.create();
    Command command = createCommand(perspective, tool);
    tool.setCommand(command);
    PerspectiveToggle toggle = new ToolPerspectiveToggle(tool, resources);
    perspective.configureToggle(toggle);
    toolbar.getItems().add(tool.getNode());
    tool.registerWithGroup(buttonGroup);
  }

  private Command createCommand(Perspective perspective, FxToggleTool tool) {
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
}