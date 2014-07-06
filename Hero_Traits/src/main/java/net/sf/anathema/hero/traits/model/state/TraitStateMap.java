package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;

public interface TraitStateMap {

  TraitState getState(Trait trait);

  TraitState getState(TraitType trait);
}
