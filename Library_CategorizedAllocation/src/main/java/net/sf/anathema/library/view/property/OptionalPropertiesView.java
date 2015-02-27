package net.sf.anathema.library.view.property;

import net.sf.anathema.library.resources.RelativePath;
import net.sf.anathema.library.view.OptionalEntriesView;

public interface OptionalPropertiesView extends OptionalEntriesView {
	OptionalPropertyItemView addItemView(String label, RelativePath removeIcon);
}
