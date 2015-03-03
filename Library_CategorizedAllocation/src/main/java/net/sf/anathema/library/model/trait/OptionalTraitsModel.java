package net.sf.anathema.library.model.trait;

import net.sf.anathema.library.model.OptionalEntriesModel;

public interface OptionalTraitsModel<
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<O>> extends OptionalEntriesModel<O, T> {	

}
