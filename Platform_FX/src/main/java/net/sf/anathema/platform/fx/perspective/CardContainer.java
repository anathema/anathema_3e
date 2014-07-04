package net.sf.anathema.platform.fx.perspective;

import javafx.scene.Node;
import net.sf.anathema.library.fx.view.FxStack;
import net.sf.anathema.library.identifier.Identifier;

public class CardContainer implements Container {


  private final Identifier title;
  private final FxStack cardPanel;

  public CardContainer(Identifier title, FxStack cardPanel) {
    this.title = title;
    this.cardPanel = cardPanel;
  }

  @Override
  public void setContent(Node node) {
    cardPanel.add(title, node);
  }
}
