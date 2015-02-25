package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.traits.model.TraitType;

public interface ThaumaturgyRitual extends TraitType {

	public final static int BASIC_RITUAL_LEVEL = 1;
	public final static int ADVANCED_RITUAL_LEVEL = 2;
	public final static int RITUAL_MAX_LEVEL = ADVANCED_RITUAL_LEVEL;
	
  boolean isLegalValue(int value);

  int getMinimumValue();

  int getMaximumValue();
}