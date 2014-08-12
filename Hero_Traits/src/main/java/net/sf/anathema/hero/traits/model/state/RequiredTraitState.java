package net.sf.anathema.hero.traits.model.state;

public interface RequiredTraitState {
  boolean satisfiesRequirement(TraitStateType newState);

  TraitStateType overrideStateIfNecessary(TraitStateType state);
}
