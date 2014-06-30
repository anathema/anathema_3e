package net.sf.anathema.points.display.overview;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import net.sf.anathema.fx.hero.overview.DefaultCategorizedOverview;
import net.sf.anathema.fx.hero.overview.FxOverviewDisplay;
import net.sf.anathema.fx.hero.overview.NullOverviewContainer;
import net.sf.anathema.hero.points.display.overview.view.CategorizedOverview;
import net.sf.anathema.hero.points.display.overview.view.OverviewContainer;
import net.sf.anathema.platform.fx.NodeHolder;

public class FxOverviewTab implements OverviewContainer, NodeHolder, FxOverviewDisplay {

  private CategorizedOverview creationOverviewView;
  private CategorizedOverview experienceOverviewView;
  private CategorizedOverview overviewView = new NullOverviewContainer();
  private BorderPane content = new BorderPane();

  @Override
  public CategorizedOverview addCreationOverviewView() {
    DefaultCategorizedOverview newView = new DefaultCategorizedOverview();
    this.creationOverviewView = newView;
    return newView;
  }

  @Override
  public CategorizedOverview addExperienceOverviewView() {
    DefaultCategorizedOverview newView = new DefaultCategorizedOverview();
    this.experienceOverviewView = newView;
    return newView;
  }

  @Override
  public void toggleOverviewView(boolean experienced) {
    this.overviewView = experienced ? experienceOverviewView : creationOverviewView;
    overviewView.showIn(this);
  }

  @Override
  public Node getNode() {
    return content;
  }

  @Override
  public void setOverviewPane(Node node) {
    content.setCenter(node);
  }
}
