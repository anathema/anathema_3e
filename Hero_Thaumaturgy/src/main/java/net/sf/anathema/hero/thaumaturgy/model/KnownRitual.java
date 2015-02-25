package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.traits.model.Trait;

public interface KnownRitual extends Trait {
	ThaumaturgyRitual getBaseOption();
	
	RitualLevel getLevel();
	
	String getDescription();
}
