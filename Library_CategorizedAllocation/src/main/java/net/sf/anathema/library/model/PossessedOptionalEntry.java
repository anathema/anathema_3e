package net.sf.anathema.library.model;

import net.sf.anathema.library.model.property.OptionalPropertyOption;

public interface PossessedOptionalEntry<O extends OptionalPropertyOption> {
	O getBaseOption();
	
	String getDescription();
}
