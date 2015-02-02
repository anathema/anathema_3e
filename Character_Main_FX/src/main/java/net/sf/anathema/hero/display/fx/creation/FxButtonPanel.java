package net.sf.anathema.hero.display.fx.creation;

import javafx.scene.Node;
import javafx.scene.control.Label;
import net.sf.anathema.hero.application.creation.ButtonPanel;
import net.sf.anathema.library.fx.tool.FxButtonTool;
import net.sf.anathema.library.interaction.model.Tool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.library.fx.layout.LayoutUtils.withoutInsets;

public class FxButtonPanel implements ButtonPanel {

  private final MigPane panel = new MigPane(withoutInsets().wrapAfter(2));


  public Node getNode() {
    return panel;
  }

  @Override
  public Tool addButton(String label) {
    FxButtonTool tool = FxButtonTool.ForToolbar();
    Node button = tool.getNode();
    panel.add(button);
    panel.add(new Label(label));
    return tool;
  }
}
