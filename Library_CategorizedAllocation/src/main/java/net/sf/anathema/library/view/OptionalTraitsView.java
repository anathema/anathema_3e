package net.sf.anathema.library.view;

import net.sf.anathema.library.resources.RelativePath;

public interface OptionalTraitsView {
  OptionalTraitEntryView addSelectionView();

  OptionalTraitItemView addKnownTrait(String label, int maxValue, RelativePath removeIcon);
}
