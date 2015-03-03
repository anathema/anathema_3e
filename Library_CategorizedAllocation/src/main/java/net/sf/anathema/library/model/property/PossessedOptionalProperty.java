package net.sf.anathema.library.model.property;

import net.sf.anathema.library.model.PossessedOptionalEntry;

public interface PossessedOptionalProperty<O extends OptionalPropertyOption> extends PossessedOptionalEntry<O> {
	boolean isLearnedAtCreation();
}
