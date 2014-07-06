package net.sf.anathema.hero.traits.model.state;

import java.util.Arrays;

import static net.sf.anathema.hero.traits.model.state.TraitStateType.Default;

public class NullTraitState implements TraitState {

  @Override
  public void addTraitStateChangedListener(TraitStateChangedListener listener) {
    // nothing to do
  }

  @Override
  public TraitStateType getType() {
    return Default;
  }

  @Override
  public boolean isCaste() {
    return false;
  }

  @Override
  public boolean isCasteOrFavored() {
    return false;
  }

  @Override
  public boolean isFavored() {
    return false;
  }

  @Override
  public void advanceState() {
    // nothing to do
  }

  @Override
  public void restore(TraitStateType type) {
    // nothing to do
  }

  @Override
  public boolean isCheapened() {
    return false;
  }

  @Override
  public boolean hasState(TraitStateType... type) {
    return Arrays.asList().contains(getType());
  }

  @Override
  public boolean isSelectableForCaste() {
    return false;
  }
}