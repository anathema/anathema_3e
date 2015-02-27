package net.sf.anathema.library.model.trait;

import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.library.model.property.AbstractOptionalPropertiesModel;
import net.sf.anathema.library.model.property.OptionalEntryCategory;

public abstract class AbstractOptionalTraitModel<
	C extends OptionalEntryCategory,
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<O>>
	extends AbstractOptionalPropertiesModel<C, O, T>
	implements OptionalTraitsModel<C, O, T> {
	
	protected AbstractOptionalTraitModel(boolean hasCategories) {
		super(hasCategories);
	}

	@Override
  protected T createEntry() {
    T trait = createPossessedEntry(getSelectedEntryOption(), currentDescription, hero);
    trait.addCurrentValueListener(new TraitValueChangedListener(change, trait));
    return trait;
  }

}
