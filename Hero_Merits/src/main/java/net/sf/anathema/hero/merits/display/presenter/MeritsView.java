package net.sf.anathema.hero.merits.display.presenter;

import net.sf.anathema.library.resources.RelativePath;

public interface MeritsView {

  MeritEntryView addSelectionView();

  MeritItemView addMerit(String label, RelativePath removeIcon);
}