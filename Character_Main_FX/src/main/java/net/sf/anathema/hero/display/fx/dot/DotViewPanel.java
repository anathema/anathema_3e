package net.sf.anathema.hero.display.fx.dot;

import javafx.scene.Node;
import net.miginfocom.layout.CC;

public interface DotViewPanel {
  void remove(Node panel);

  void add(Node panel);

  void add(Node panel, CC constraints);
}
