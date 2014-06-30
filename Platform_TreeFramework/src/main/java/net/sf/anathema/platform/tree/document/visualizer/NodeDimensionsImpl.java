package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.framework.ui.Area;

public class NodeDimensionsImpl implements NodeDimensions {

  @Override
  public Area getNodeDimension() {
    return new Area(180, 90);
  }

  @Override
  public Area getGapDimension() {
    return new Area(25, 50);
  }

  @Override
  public final Area getVerticalLineDimension() {
    return new Area(getGapDimension().width, getNodeDimension().height);
  }
}
