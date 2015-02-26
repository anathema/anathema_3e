package net.sf.anathema.library.model;

import net.sf.anathema.hero.traits.model.TraitType;

public interface OptionalTraitOption extends TraitType {
	boolean isLegalValue(int value);

  int getMinimumValue();

  int getMaximumValue();
}
