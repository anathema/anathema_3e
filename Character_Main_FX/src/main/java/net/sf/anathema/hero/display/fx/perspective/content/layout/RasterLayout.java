package net.sf.anathema.hero.display.fx.perspective.content.layout;

import javafx.scene.Node;

import org.tbee.javafx.scene.layout.MigPane;

public interface RasterLayout {

  void setLayoutConstraints(MigPane pane);

  void addNext(MigPane parent, Node child);
}
