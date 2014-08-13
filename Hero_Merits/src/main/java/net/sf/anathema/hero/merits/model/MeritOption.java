package net.sf.anathema.hero.merits.model;

import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitType;

public interface MeritOption extends TraitType {

	static int MAX_MERIT_RATING = 5;
	
	MeritCategory getType();

	boolean allowsRepurchase();
	
	boolean isHeroEligible(Hero hero);
	
	boolean isLegalValue(int value);
	
	List<String> getContingentTraitTypes();
	
	int getMinimumValue();
	
	int getMaximumValue();
}
