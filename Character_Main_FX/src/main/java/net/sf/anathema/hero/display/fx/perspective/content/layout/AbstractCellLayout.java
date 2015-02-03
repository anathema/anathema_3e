package net.sf.anathema.hero.display.fx.perspective.content.layout;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import org.tbee.javafx.scene.layout.MigPane;

public abstract class AbstractCellLayout implements CellLayout {

  @Override
  public final void add(MigPane parent, Node child) {
    parent.add(child, createCellConstraint());
  }

  protected abstract CC createCellConstraint();

  protected final CC createDefaultCellConstraints() {
    return new CC().alignY("t");
  }
}

