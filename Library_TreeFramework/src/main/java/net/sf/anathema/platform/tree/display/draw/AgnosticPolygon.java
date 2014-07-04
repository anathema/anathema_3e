package net.sf.anathema.platform.tree.display.draw;

import net.sf.anathema.library.number.Coordinate;
import net.sf.anathema.library.presenter.RGBColor;

public interface AgnosticPolygon {
  void whenToggledDo(Runnable runnable);

  void fill(RGBColor fillColor);

  void setAlpha(int alpha);

  void setText(String id);

  void moveBy(int x, int y);

  void position(ShapeWithPosition control);

  boolean contains(Coordinate point);
}