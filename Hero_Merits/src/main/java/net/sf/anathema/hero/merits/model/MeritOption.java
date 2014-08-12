package net.sf.anathema.hero.merits.model;

import net.sf.anathema.library.identifier.Identifier;

public interface MeritOption extends Identifier {

	static int MAX_MERIT_RATING = 5;
	
	MeritCategory getType();

	boolean allowsRepurchase();
}
