package net.sf.anathema.library.model.trait;

import net.sf.anathema.library.model.property.OptionalPropertiesModel;
import net.sf.anathema.library.model.property.OptionalEntryCategory;

public interface OptionalTraitsModel<
	C extends OptionalEntryCategory,
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<O>> extends OptionalPropertiesModel<C, O, T> {	

}
