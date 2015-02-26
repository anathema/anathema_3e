package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.library.model.KnownOptionalTrait;

public interface KnownRitual extends KnownOptionalTrait<ThaumaturgyRitual> {
	RitualLevel getLevel();
}
