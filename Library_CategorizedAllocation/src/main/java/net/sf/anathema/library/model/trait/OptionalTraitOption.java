package net.sf.anathema.library.model.trait;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.property.OptionalPropertyOption;

public interface OptionalTraitOption extends OptionalPropertyOption, TraitType {
	boolean isLegalValue(int value);

  int getMinimumValue();

  int getMaximumValue();
}
