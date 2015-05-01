package net.sf.anathema.hero.display.fx.perspective.content.layout;

import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

public class ConfigurableLayout implements RasterLayout {

  private final int columnCount;
  private final CellLayoutIterator cellLayouts;

  public ConfigurableLayout() {
    this(3);
  }

  public ConfigurableLayout(int columnCount, CellLayout... cellLayouts) {
    this.cellLayouts = new CellLayoutIterator(cellLayouts);
    this.columnCount = columnCount;
  }

  @Override
  public void setLayoutConstraints(MigPane pane) {
    pane.setLayoutConstraints(new LC().insets("0").wrapAfter(columnCount).gridGap("15", "10"));
    pane.setColumnConstraints(new AC().fill());
    pane.setRowConstraints(new AC());
  }

  @Override
  public void addNext(MigPane parent, Node child) {
    cellLayouts.next().add(parent, child);
  }
}
