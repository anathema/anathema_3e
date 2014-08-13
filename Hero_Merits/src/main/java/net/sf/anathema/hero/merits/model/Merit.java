package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.traits.model.Trait;

public interface Merit extends Trait {
	MeritOption getBaseOption();
	
	String getDescription();
}
