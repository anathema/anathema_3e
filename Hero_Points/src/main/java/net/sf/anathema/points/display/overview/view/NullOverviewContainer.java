package net.sf.anathema.points.display.overview.view;

import net.sf.anathema.character.framework.library.overview.OverviewCategory;

public class NullOverviewContainer implements CategorizedOverview {
  @Override
  public OverviewCategory addOverviewCategory(String borderLabel) {
    return new FxOverviewCategory(null, null);
  }

  @Override
  public void showIn(OverviewDisplay characterPane) {
    //nothing to do
  }
}
