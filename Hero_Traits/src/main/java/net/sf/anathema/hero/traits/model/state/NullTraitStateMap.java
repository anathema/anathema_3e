package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.traits.model.Trait;

import java.util.function.Consumer;

import static net.sf.anathema.hero.traits.model.state.TraitState.Default;

public class NullTraitStateMap implements TraitStateMap {
  @Override
  public void addTraitStateChangedListener(Trait trait, TraitStateChangedListener listener) {
    // nothing to do
  }

  @Override
  public TraitState getType(Trait trait) {
    return Default;
  }

  @Override
  public boolean isCaste(Trait trait) {
    return false;
  }

  @Override
  public boolean isFavored(Trait trait) {
    return false;
  }

  @Override
  public boolean isCasteOrFavored(Trait trait) {
    return false;
  }

  @Override
  public void changeStateTo(Trait trait, TraitState state) {
    // nothing to do
  }

  @Override
  public void advanceFavorableState(Trait trait) {
    // nothing to do
  }

  @Override
  public void setFavored(Trait trait, boolean favored) {
    // nothing to do
  }

  @Override
  public int getMinimalValue(Trait trait) {
    return trait.getMinimalValue();
  }

  @Override
  public void forEach(Consumer<TraitStateModel> model) {
    // nothing to do
  }
}
