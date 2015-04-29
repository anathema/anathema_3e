package net.sf.anathema.hero.display.fx.perspective.content.layout;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.library.fx.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class GreedyLayout implements RasterLayout {
  @Override
  public void setLayoutConstraints(MigPane pane) {
    pane.setLayoutConstraints(LayoutUtils.fillWithoutInsets());
  }

  @Override
  public void addNext(MigPane parent, Node child) {
    parent.add(child, new CC().grow().push());
  }
}
