package net.sf.anathema.hero.display.fx.perspective.content.layout;

import java.util.Arrays;
import java.util.List;

public class CellLayoutIterator {

  private final List<CellLayout> layouts;
  private  int index = 0;

  public CellLayoutIterator(CellLayout... layouts) {
    this.layouts = Arrays.asList(layouts);
  }

  public CellLayout next() {
    if (index < layouts.size()) {
      return layouts.get(index++);
    }
    return new DefaultCell();
  }
}
