package net.sf.anathema.platform.tree.document;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.tree.document.visualizer.NodeDimensions;
import net.sf.anathema.platform.tree.view.container.Cascade;

public interface CascadeFactory {

  Cascade createCascade(IRegularNode[] nodes, NodeDimensions properties);
}