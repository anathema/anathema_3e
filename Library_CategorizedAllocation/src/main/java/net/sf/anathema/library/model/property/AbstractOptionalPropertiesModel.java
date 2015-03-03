package net.sf.anathema.library.model.property;

import net.sf.anathema.library.model.AbstractOptionalEntriesModel;

public abstract class AbstractOptionalPropertiesModel<
	O extends OptionalPropertyOption,
	T extends PossessedOptionalProperty<O>>
	extends AbstractOptionalEntriesModel<O, T>
	implements OptionalPropertiesModel<O, T> {

	protected AbstractOptionalPropertiesModel(boolean hasCategories) {
		super(hasCategories);
	}
	
  
}
