package net.sf.anathema.hero.traits.model.state;

public interface TraitState {

  void addTraitStateChangedListener(TraitStateChangedListener listener);

  TraitStateType getType();

  void advanceState();

  void restore(TraitStateType type);

  boolean isCheapened();

  boolean hasState(TraitStateType... type);

  boolean isSelectableForCaste();
}