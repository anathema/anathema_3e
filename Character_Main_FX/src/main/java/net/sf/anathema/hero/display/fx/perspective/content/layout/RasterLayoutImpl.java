package net.sf.anathema.hero.display.fx.perspective.content.layout;

import javafx.scene.Node;

import net.miginfocom.layout.LC;
import net.sf.anathema.library.fx.layout.LayoutUtils;

import org.tbee.javafx.scene.layout.MigPane;

public class RasterLayoutImpl implements RasterLayout {

  private final int columnCount;
  private final CellLayoutIterator cellLayouts;

  public RasterLayoutImpl() {
    this(3);
  }

  public RasterLayoutImpl(int columnCount, CellLayout... cellLayouts) {
    this.cellLayouts = new CellLayoutIterator(cellLayouts);
    this.columnCount = columnCount;
  }

  @Override
  public void setLayoutConstraints(MigPane pane) {
    LC layoutConstraints = LayoutUtils.fillWithoutInsets().wrapAfter(columnCount).gridGap("15", "4");
    pane.setLayoutConstraints(layoutConstraints);
  }

  @Override
  public void addNext(MigPane parent, Node child) {
    cellLayouts.next().add(parent, child);
  }
}
