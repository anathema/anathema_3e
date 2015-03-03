package net.sf.anathema.hero.display.fx.creation;

import javafx.scene.Node;

import net.sf.anathema.hero.application.creation.ButtonPanel;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.interaction.model.Tool;

import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxButtonPanel implements ButtonPanel {

  private final MigPane panel = new MigPane(withoutInsets().wrapAfter(1));

  public Node getNode() {
    return panel;
  }

  @Override
  public Tool addButton(String label) {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    tool.setText(label);
    Node button = tool.getNode();
    button.getStyleClass().add("new-hero-splat-selector");
    panel.add(button);
    return tool;
  }
}
