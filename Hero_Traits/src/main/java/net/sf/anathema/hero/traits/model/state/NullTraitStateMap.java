package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;

public class NullTraitStateMap implements TraitStateMap {
  @Override
  public TraitState getState(Trait trait) {
    return new NullTraitState();
  }

  @Override
  public TraitState getState(TraitType trait) {
    return new NullTraitState();
  }
}
