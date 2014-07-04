package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.library.number.Coordinate;

public interface MouseMotionClosure {
  void mouseDragged(MouseButton button, Coordinate coordinate);

  void mouseMoved(Coordinate coordinate);
}