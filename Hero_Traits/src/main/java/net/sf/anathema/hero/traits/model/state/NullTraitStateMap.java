package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;

public class NullTraitStateMap implements TraitStateMap {
  @Override
  public TraitState getTraitState(Trait trait) {
    return new NullTraitState();
  }

  @Override
  public TraitState getTraitState(TraitType trait) {
    return new NullTraitState();
  }
}
