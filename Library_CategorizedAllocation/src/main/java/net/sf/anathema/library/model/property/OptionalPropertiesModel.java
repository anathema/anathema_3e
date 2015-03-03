package net.sf.anathema.library.model.property;

import net.sf.anathema.hero.individual.model.HeroModel;
import net.sf.anathema.library.model.OptionalEntriesModel;
import net.sf.anathema.library.model.RemovableEntryModel;

public interface OptionalPropertiesModel<
	O extends OptionalPropertyOption,
	T extends PossessedOptionalProperty<O>>
	extends RemovableEntryModel<T>, HeroModel,
	OptionalEntriesModel<O, T> {
}
