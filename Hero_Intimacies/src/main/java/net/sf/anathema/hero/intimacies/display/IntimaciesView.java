package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.framework.library.overview.OverviewCategory;
import net.sf.anathema.hero.languages.display.presenter.RemovableEntryView;
import net.sf.anathema.library.resources.RelativePath;

public interface IntimaciesView {

  IntimacyEntryView addSelectionView(String labelText);

  OverviewCategory addOverview(String border);

  void setOverview(OverviewCategory overviewView);

  RemovableEntryView addIntimacy(String name, RelativePath removeIcon);
}