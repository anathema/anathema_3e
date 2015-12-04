package net.sf.anathema.hero.traits.model.state;

public interface TraitState {

  void addTraitStateChangedListener(TraitStateChangedListener listener);

  TraitStateType getType();

  void advanceState();

  void restore(TraitStateType type);

  boolean isCheapened();

  boolean hasState(TraitStateType... type);

  boolean isSelectableForState(TraitStateType type);

  /** Whether this trait state counts towards splat limits on the type. Example: Favored-MA derives from Favored-Brawl so doesn't count towards the 5-favored limit. */
  boolean countsTowardsLimitsOn(TraitStateType type);
}