package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.library.presenter.AgnosticUIConfiguration;
import net.sf.anathema.library.view.ObjectSelectionView;

public interface SheetPreferenceView {
  ObjectSelectionView<PageSize> addObjectSelectionView(String title, AgnosticUIConfiguration<PageSize> uiConfiguration);
}
