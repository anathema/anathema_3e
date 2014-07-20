package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.library.number.Area;
import net.sf.anathema.platform.tree.document.components.ILayer;
import net.sf.anathema.platform.tree.document.visualizer.NodeDimensions;
import net.sf.anathema.platform.tree.document.visualizer.TreeDimensionCalculator;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraph;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;

public class AgnosticGraphFactory implements VisualizedGraphFactory {
  private TreeDimensionCalculator calculator;
  private final NodeDimensions properties;

  public AgnosticGraphFactory(NodeDimensions properties) {
    this.properties = properties;
    this.calculator = new TreeDimensionCalculator(properties);
  }

  @Override
  public VisualizedGraph create(ILayer... layers) {
    boolean singleNode = layers.length == 1 && layers[0].getNodes().hasOnlyOneNode();
    return new AgnosticVisualizedGraph(createCascade(layers), calculateSize(layers), singleNode);
  }

  private Area calculateSize(ILayer... layers) {
    return calculator.getTreeDimension(layers);
  }

  private DefaultContainerCascade createCascade(ILayer... layers) {
    return new LayerCascadeCreator(properties).create(layers);
  }
}