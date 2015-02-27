package net.sf.anathema.library.model;

import java.util.List;

import net.sf.anathema.library.model.property.OptionalEntryCategory;
import net.sf.anathema.library.model.property.OptionalPropertyOption;

public interface OptionalEntryCache<
	C extends OptionalEntryCategory,
	O extends OptionalPropertyOption> {

	List<O> getAllOptions();
	
	List<O> getAllOptionsForCategory(C category);
	
	O getOptionByReference(OptionalEntryReference reference);
}
