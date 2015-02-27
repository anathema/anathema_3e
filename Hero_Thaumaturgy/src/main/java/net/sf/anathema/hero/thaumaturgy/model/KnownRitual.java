package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.library.model.trait.PossessedOptionalTrait;

public interface KnownRitual extends PossessedOptionalTrait<ThaumaturgyRitual> {
	RitualLevel getLevel();
}
