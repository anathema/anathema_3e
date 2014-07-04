package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.library.number.Coordinate;

public interface InteractiveGraphicsElement extends GraphicsElement {
  boolean contains(Coordinate p);

  void toggle();
}