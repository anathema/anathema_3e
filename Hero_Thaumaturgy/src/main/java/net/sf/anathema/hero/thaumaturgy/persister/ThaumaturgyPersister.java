package net.sf.anathema.hero.thaumaturgy.persister;

import net.sf.anathema.hero.thaumaturgy.model.KnownRitual;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyModel;
import net.sf.anathema.hero.thaumaturgy.model.ThaumaturgyRitual;
import net.sf.anathema.library.identifier.Identifier;
import net.sf.anathema.library.model.NullCategory;
import net.sf.anathema.library.persister.OptionalTraitsPersister;

public class ThaumaturgyPersister
	extends OptionalTraitsPersister<NullCategory, ThaumaturgyRitual, KnownRitual, ThaumaturgyModel, ThaumaturgyPto> {

	public ThaumaturgyPersister() {
		super("rituals", ThaumaturgyPto.class);
	}

	@Override
	public Identifier getModelId() {
		return ThaumaturgyModel.ID;
	}

	@Override
	protected ThaumaturgyPto createNewPto() {
		return new ThaumaturgyPto();
	}
}
