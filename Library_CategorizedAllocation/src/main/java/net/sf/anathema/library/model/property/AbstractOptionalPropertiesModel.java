package net.sf.anathema.library.model.property;

import net.sf.anathema.library.model.AbstractOptionalEntriesModel;
import net.sf.anathema.library.model.OptionalEntryCategory;

public abstract class AbstractOptionalPropertiesModel<
	C extends OptionalEntryCategory,
	O extends OptionalPropertyOption,
	T extends PossessedOptionalProperty<O>>
	extends AbstractOptionalEntriesModel<C, O, T>
	implements OptionalPropertiesModel<C, O, T> {

	protected AbstractOptionalPropertiesModel(boolean hasCategories) {
		super(hasCategories);
	}
	
  
}
