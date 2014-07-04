package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.library.number.Coordinate;

public interface MouseWheelClosure {
  void mouseWheelMoved(int wheelClicks, Coordinate coordinate);
}
