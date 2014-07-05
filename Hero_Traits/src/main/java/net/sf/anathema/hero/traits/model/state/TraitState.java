package net.sf.anathema.hero.traits.model.state;

public interface TraitState {

  void addTraitStateChangedListener(TraitStateChangedListener listener);

  TraitStateType getType();

  boolean isCaste();

  boolean isCasteOrFavored();

  boolean isFavored();

  void advanceState();

  void setFavored(boolean favored);

  boolean isCheapened();

  boolean hasState(TraitStateType... type);

  boolean isSelectableForCaste();
}