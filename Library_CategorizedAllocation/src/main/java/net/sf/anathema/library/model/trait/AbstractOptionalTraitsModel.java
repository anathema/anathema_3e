package net.sf.anathema.library.model.trait;

import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.library.model.AbstractOptionalEntriesModel;
import net.sf.anathema.library.model.OptionalEntryCategory;

public abstract class AbstractOptionalTraitsModel<
	O extends OptionalTraitOption,
	T extends PossessedOptionalTrait<O>>
	extends AbstractOptionalEntriesModel<O, T>
	implements OptionalTraitsModel<O, T> {
	
	protected AbstractOptionalTraitsModel(boolean hasCategories) {
		super(hasCategories);
	}

	@Override
  protected T createEntry() {
    T trait = createPossessedEntry(getSelectedEntryOption(), currentDescription, hero);
    trait.addCurrentValueListener(new TraitValueChangedListener(change, trait));
    return trait;
  }

}
