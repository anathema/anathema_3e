package net.sf.anathema.hero.languages.display.presenter;

import net.sf.anathema.hero.framework.library.overview.OverviewCategory;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.resources.RelativePath;

public interface LanguagesView {

  ObjectSelectionViewWithTool<Object> addSelectionView(String labelText, AgnosticUIConfiguration<Object> renderer);

  OverviewCategory addOverview(String border);

  RemovableEntryView addEntryView(RelativePath removeIcon, String string);
}