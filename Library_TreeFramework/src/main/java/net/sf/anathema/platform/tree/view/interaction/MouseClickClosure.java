package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.library.number.Coordinate;

public interface MouseClickClosure {

  void mouseClicked(MouseButton button, MetaKey key, Coordinate coordinate, int clickCount);
}
