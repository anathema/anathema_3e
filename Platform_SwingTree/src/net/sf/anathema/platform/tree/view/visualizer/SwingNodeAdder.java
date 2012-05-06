package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.visualizer.NodeAdder;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import net.sf.anathema.platform.tree.view.container.IdentifiedPolygon;

import java.awt.Dimension;

public class SwingNodeAdder implements NodeAdder<DefaultContainerCascade> {
  private final String id;
  private final Dimension dimension;
  private final int xPosition;
  private final int yPosition;

  public SwingNodeAdder(String id, Dimension dimension, int xPosition, int yPosition) {
    this.id = id;
    this.dimension = dimension;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }

  @Override
  public void addTo(DefaultContainerCascade defaultContainerCascade) {
    CharmPolygon polygon = new CharmPolygon(xPosition, yPosition);
    IdentifiedPolygon node = new IdentifiedPolygon(polygon, id);
    defaultContainerCascade.add(node);
  }
}
