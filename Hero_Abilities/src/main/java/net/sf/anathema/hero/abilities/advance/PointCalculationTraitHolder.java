package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.traits.display.Traits;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateType;

public interface PointCalculationTraitHolder {

  TraitState getState(Trait trait);

  Traits getAll();

  Iterable<TraitStateType> getAvailableTraitStates();

  boolean contains(Trait trait);
}