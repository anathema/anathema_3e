package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.traits.display.Traits;

public interface TraitMap {

  Trait getTrait(TraitType traitType);

  Traits getTraits(TraitType... traitType);

  Traits getAll();
}
