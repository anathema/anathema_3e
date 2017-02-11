package net.sf.anathema.hero.traits.model.state;

import java.util.Arrays;

public class NullTraitState implements TraitState {

  @Override
  public void addTraitStateChangedListener(TraitStateChangedListener listener) {
    // nothing to do
  }

  @Override
  public TraitStateType getType() {
    return DefaultTraitStateType.Default;
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
  public boolean isSelectableForState(TraitStateType type) {
    return false;
  }

  @Override
  public boolean countsTowardsLimitsOn(TraitStateType type) {
    return false;
  }
}