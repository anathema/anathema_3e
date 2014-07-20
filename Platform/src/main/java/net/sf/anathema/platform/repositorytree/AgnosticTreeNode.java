package net.sf.anathema.platform.repositorytree;

public interface AgnosticTreeNode {
  AgnosticTreeNode addChildNode(Object type);

  Object getObject();

  void remove();

  Iterable<AgnosticTreeNode> getChildren();
}
