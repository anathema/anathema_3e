package net.sf.anathema.library.model.trait;

import net.sf.anathema.library.model.OptionalEntriesModel;
import net.sf.anathema.library.model.OptionalEntryCategory;

public interface OptionalTraitsModel<
	C extends OptionalEntryCategory,
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<? extends OptionalTraitOption>> extends OptionalEntriesModel<C, O, T> {	

}
