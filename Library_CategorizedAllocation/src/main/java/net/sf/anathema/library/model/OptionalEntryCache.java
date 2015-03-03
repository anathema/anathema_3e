package net.sf.anathema.library.model;

import java.util.List;

public interface OptionalEntryCache<
	C extends OptionalEntryCategory,
	O extends OptionalEntryOption> {

	List<O> getAllOptions();
	
	List<O> getAllOptionsForCategory(C category);
	
	O getOptionByReference(OptionalEntryReference reference);
}
