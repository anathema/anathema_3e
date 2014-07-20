package net.sf.anathema.platform.tree.document.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisualizableNodes implements Iterable<IVisualizableNode> {
  private final List<IVisualizableNode> nodes = new ArrayList<>();

  public VisualizableNodes(List<IVisualizableNode> nodes) {
    this.nodes.addAll(nodes);
  }

  public IVisualizableNode getFirst() {
    return nodes.get(0);
  }

  public IVisualizableNode getLast() {
    return nodes.get(nodes.size() - 1);
  }

  @Override
  public Iterator<IVisualizableNode> iterator() {
    return nodes.iterator();
  }

  public boolean hasOnlyOneNode() {
    return nodes.size() == 1;
  }

  public List<IVisualizableNode> asList() {
    return nodes;
  }
}