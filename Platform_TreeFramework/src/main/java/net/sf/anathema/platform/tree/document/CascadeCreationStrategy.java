package net.sf.anathema.platform.tree.document;

import net.sf.anathema.platform.tree.document.visualizer.NodeDimensions;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;

public interface CascadeCreationStrategy {

  VisualizedGraphFactory getFactoryForVisualizedGraphs(NodeDimensions properties);
}