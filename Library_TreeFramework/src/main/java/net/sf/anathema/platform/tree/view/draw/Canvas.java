package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.library.number.Area;
import net.sf.anathema.library.number.Coordinate;
import net.sf.anathema.library.number.Rectangle;
import net.sf.anathema.library.number.Width;
import net.sf.anathema.library.presenter.FontStyle;
import net.sf.anathema.library.presenter.RGBColor;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;

public interface Canvas {
  void setStrokeWidth(Width width);

  void setColor(RGBColor color);

  void drawPolyline(Iterable<Coordinate> coordinates);

  void fill(TransformedShape shape);

  void draw(AgnosticShape shape);

  Area measureText(String text);

  void drawString(String text, Coordinate coordinate);

  void setFontStyle(FontStyle style, int textSize);

  Rectangle calculateBounds(AgnosticShape shape);
}