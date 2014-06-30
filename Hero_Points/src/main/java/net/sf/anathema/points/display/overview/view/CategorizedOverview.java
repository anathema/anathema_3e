package net.sf.anathema.points.display.overview.view;

import net.sf.anathema.character.framework.library.overview.OverviewCategory;

public interface CategorizedOverview {

  OverviewCategory addOverviewCategory(String borderLabel);

  void showIn(OverviewDisplay characterPane);
}