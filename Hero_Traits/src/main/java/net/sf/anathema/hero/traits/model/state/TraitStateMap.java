package net.sf.anathema.hero.traits.model.state;

import java.util.function.Consumer;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitType;

public interface TraitStateMap {

  TraitState getState(Trait trait);

  TraitState getState(TraitType trait);
}
