package net.sf.anathema.hero.traits.model.state;

public class NoRequiredState implements RequiredTraitState {
  @Override
  public boolean satisfiesRequirement(TraitStateType newState) {
    return true;
  }

  @Override
  public TraitStateType overrideStateIfNecessary(TraitStateType state) {
    return state;
  }
}
