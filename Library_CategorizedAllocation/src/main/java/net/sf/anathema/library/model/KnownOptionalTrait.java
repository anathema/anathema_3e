package net.sf.anathema.library.model;

import net.sf.anathema.hero.traits.model.Trait;

public interface KnownOptionalTrait<O> extends Trait {
	O getBaseOption();
	
	String getDescription();
}
