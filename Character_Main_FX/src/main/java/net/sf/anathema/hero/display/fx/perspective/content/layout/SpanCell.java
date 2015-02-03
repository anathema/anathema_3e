package net.sf.anathema.hero.display.fx.perspective.content.layout;

import net.miginfocom.layout.CC;

public class SpanCell extends AbstractCellLayout {

  private int columnSpan;
  private int rowSpan;

  public SpanCell(int columnSpan, int rowSpan) {
    this.columnSpan = columnSpan;
    this.rowSpan = rowSpan;
  }

  @Override
  protected CC createCellConstraint() {
    return createDefaultCellConstraints().spanX(columnSpan).spanY(rowSpan);
  }
}
