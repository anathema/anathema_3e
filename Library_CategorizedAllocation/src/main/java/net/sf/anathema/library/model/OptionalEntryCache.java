package net.sf.anathema.library.model;

import java.util.List;

public interface OptionalEntryCache<
	O extends OptionalEntryOption> {

	List<O> getAllOptions();
	
	List<O> getAllOptionsForCategory(OptionalEntryCategory category);
	
	O getOptionByReference(OptionalEntryReference reference);
}
