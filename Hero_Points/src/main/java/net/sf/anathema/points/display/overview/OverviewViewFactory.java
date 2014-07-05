package net.sf.anathema.points.display.overview;

import net.sf.anathema.hero.individual.view.SubViewFactory;
import net.sf.anathema.library.dependencies.Produces;
import net.sf.anathema.points.display.overview.view.OverviewContainer;

@Produces(OverviewContainer.class)
public class OverviewViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxOverviewTab();
  }
}