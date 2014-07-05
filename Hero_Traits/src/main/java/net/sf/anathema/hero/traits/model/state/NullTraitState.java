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
  public void clearCaste() {
    // nothing to do
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
  public void changeStateTo(TraitStateType state) {
    // nothing to do
  }
  
  @Override
  public void advanceFavorableState() {
    // nothing to do
  }

  @Override
  public void setFavored(boolean favored) {
    // nothing to do
  }

  @Override
  public int getMinimalValue() {
    return 0;
  }

  @Override
  public boolean isCheapened() {
    return false;
  }

  @Override
  public boolean hasState(TraitStateType... type) {
    return Arrays.asList().contains(getType());
  }
}