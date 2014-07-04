package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.hero.individual.overview.OverviewCategory;
import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.RemovableEntryView;

public interface IntimaciesView {

  IntimacyEntryView addSelectionView(String labelText);

  OverviewCategory addOverview(String border);

  void setOverview(OverviewCategory overviewView);

  RemovableEntryView addIntimacy(String name, RelativePath removeIcon);
}