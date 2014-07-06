package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateChangedListener;

import java.util.function.Consumer;

public interface TraitStateCollection {

  void addTraitStateChangedListener(Trait trait, TraitStateChangedListener listener);

  void forEach(Consumer<TraitState> consumer);
}
