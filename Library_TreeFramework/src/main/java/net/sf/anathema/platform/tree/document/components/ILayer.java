package net.sf.anathema.platform.tree.document.components;

import java.util.List;

public interface ILayer {

  void addNode(IVisualizableNode node);

  void setNodePosition(IVisualizableNode node, int centralPosition);

  void setFollowUp(ILayer layer);

  void setNodeOnNextFreePosition(IVisualizableNode node);

  void positionNodes();

  VisualizableNodes getNodes();

  int getYPosition();

  int getWidth();

  void unrollHorizontalMetanodes();

  void shiftRight(int requiredShift);

  void shiftRightRecursivelyWithThreshold(int threshold, int requiredShift);

  int getOverlapFreePosition(IVisualizableNode node);

  Integer getPreviousNodeRightExtreme(IVisualizableNode node);

  Integer getNextNodeLeftExtreme(IVisualizableNode node);

  ILayer getPreviousLayer();

  void setPreviousLayer(ILayer layer);

  void positionNode(IVisualizableNode node);

  IVisualizableNode getPreviousNode(IVisualizableNode node);

  IVisualizableNode getNextNode(IVisualizableNode node);

  void moveNodeTo(IVisualizableNode node, int xPosition);

  boolean isBottomMostLayer();
}