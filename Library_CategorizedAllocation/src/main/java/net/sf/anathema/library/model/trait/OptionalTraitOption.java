package net.sf.anathema.library.model.trait;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.library.model.OptionalEntryOption;

public interface OptionalTraitOption extends OptionalEntryOption, TraitType {
  boolean isLegalValue(int value);

  int getMinimumValue();

  int getMaximumValue();
}
