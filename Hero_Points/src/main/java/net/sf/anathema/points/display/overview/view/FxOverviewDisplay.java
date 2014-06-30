package net.sf.anathema.points.display.overview.view;

import javafx.scene.Node;
import net.sf.anathema.hero.points.display.overview.view.OverviewDisplay;

public interface FxOverviewDisplay extends OverviewDisplay{
  void setOverviewPane(Node node);
}