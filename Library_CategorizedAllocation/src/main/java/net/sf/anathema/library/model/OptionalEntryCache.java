package net.sf.anathema.library.model;

import net.sf.anathema.library.model.property.OptionalPropertyOption;

import java.util.List;

public interface OptionalEntryCache<
	C extends OptionalEntryCategory,
	O extends OptionalPropertyOption> {

	List<O> getAllOptions();
	
	List<O> getAllOptionsForCategory(C category);
	
	O getOptionByReference(OptionalEntryReference reference);
}
