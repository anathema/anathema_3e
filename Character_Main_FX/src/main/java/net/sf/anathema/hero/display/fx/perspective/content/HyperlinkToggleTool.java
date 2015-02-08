package net.sf.anathema.hero.display.fx.perspective.content;

import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import net.sf.anathema.library.fx.tool.FxBaseTool;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.view.Style;

public class HyperlinkToggleTool extends FxBaseTool implements ToggleTool {

  public HyperlinkToggleTool() {
    super(new Hyperlink(), new ImageView());
  }

  @Override
  public void setStyle(Style style) {
    getNode().getStyleClass().add(style.style);
  }

  @Override
  public void select() {
    getNode().setDisable(true);
  }

  @Override
  public void deselect() {
    getNode().setDisable(false);
  }
}
