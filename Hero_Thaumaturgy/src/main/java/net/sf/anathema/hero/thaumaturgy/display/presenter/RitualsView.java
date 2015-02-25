package net.sf.anathema.hero.thaumaturgy.display.presenter;

import net.sf.anathema.library.resources.RelativePath;

public interface RitualsView {

  RitualEntryView addSelectionView();

  RitualItemView addMerit(String label, RelativePath removeIcon);
}