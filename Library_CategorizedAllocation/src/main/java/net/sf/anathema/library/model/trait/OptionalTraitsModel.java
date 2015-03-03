package net.sf.anathema.library.model.trait;

import net.sf.anathema.library.model.OptionalEntriesModel;

public interface OptionalTraitsModel<
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<? extends OptionalTraitOption>> extends OptionalEntriesModel<O, T> {	

}
