package net.sf.anathema.hero.merits.model.requirements;

import net.sf.anathema.hero.individual.model.Hero;

public interface MeritRequirement {
	
	boolean isSatisfied(Hero hero);
}
