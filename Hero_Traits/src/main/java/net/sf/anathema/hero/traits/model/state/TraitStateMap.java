package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.traits.model.Trait;

import java.util.function.Consumer;

public interface TraitStateMap {

  void addTraitStateChangedListener(Trait trait, TraitStateChangedListener listener);

  TraitState getState(Trait trait);

  boolean hasState(Trait trait, TraitState ... states);

  boolean isFavored(Trait trait);

  boolean isCasteOrFavored(Trait trait);

  void changeStateTo(Trait trait, TraitState state);

  void advanceFavorableState(Trait trait);

  void setFavored(Trait trait, boolean favored);

  int getMinimalValue(Trait trait);

  void forEach(Consumer<TraitStateModel> model);
}
