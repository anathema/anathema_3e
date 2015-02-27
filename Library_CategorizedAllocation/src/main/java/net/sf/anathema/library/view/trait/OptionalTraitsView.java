package net.sf.anathema.library.view.trait;

import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.OptionalEntriesView;

public interface OptionalTraitsView extends OptionalEntriesView {

  OptionalTraitItemView addItemView(String label, int maxValue, RelativePath removeIcon);
}
