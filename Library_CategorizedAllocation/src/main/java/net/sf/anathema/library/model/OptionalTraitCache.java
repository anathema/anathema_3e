package net.sf.anathema.library.model;

import java.util.List;

public interface OptionalTraitCache<
	C extends OptionalTraitCategory,
	O extends OptionalTraitOption> {

	List<O> getAllOptions();
	
	List<O> getAllOptionsForCategory(C category);
	
	O getOptionByReference(OptionalTraitReference reference);
}
