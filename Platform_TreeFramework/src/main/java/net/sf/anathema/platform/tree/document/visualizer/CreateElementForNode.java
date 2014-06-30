package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.document.components.*;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

public class CreateElementForNode implements IVisualizableNodeVisitor {

  private final ILayer layer;
  private final DefaultContainerCascade parent;
  private final NodeDimensions properties;

  private final NodeAdderFactory adderFactory;

  public CreateElementForNode(ILayer layer, NodeDimensions properties, DefaultContainerCascade parent,
                               NodeAdderFactory factory) {
    this.layer = layer;
    this.parent = parent;
    this.properties = properties;
    this.adderFactory = factory;
  }

  @Override
  public void visitHorizontalMetaNode(HorizontalMetaNode visitedNode) {
    throw new UnsupportedOperationException("Unroll meta nodes before positioning.");
  }

  @Override
  public void visitSingleNode(VisualizableNode visitedNode) {
    NodeAdder adder = adderFactory.create(visitedNode.getId(), properties.getNodeDimension(),
            visitedNode.getPosition(), layer.getYPosition());
    adder.addTo(parent);
  }

  @Override
  public void visitDummyNode(VisualizableDummyNode visitedNode) {
    //nothing to do
  }
}