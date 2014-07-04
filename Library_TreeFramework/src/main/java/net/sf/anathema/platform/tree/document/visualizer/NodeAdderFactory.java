package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.library.number.Area;

public interface NodeAdderFactory {

  NodeAdder create(String id, Area dimension, int xPosition, int yPosition);
}