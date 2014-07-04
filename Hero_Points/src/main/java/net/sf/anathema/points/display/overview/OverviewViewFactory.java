package net.sf.anathema.points.display.overview;

import net.sf.anathema.hero.application.SubViewFactory;
import net.sf.anathema.library.autocollect.Produces;
import net.sf.anathema.points.display.overview.view.OverviewContainer;

@Produces(OverviewContainer.class)
public class OverviewViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxOverviewTab();
  }
}