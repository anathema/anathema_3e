package net.sf.anathema.hero.merits.model.requirements;

import net.sf.anathema.hero.individual.model.Hero;

import java.util.List;

public interface MeritRequirement {
	
	boolean isSatisfied(Hero hero);
	
	List<String> getContingentTraitTypes();
}
