package net.sf.anathema.hero.traits.model.state;

public interface TraitState {

  void addTraitStateChangedListener(TraitStateChangedListener listener);

  TraitStateType getType();

  void clearCaste();

  boolean isCaste();

  boolean isCasteOrFavored();

  boolean isFavored();

  void changeStateTo(TraitStateType state);
  
  void advanceState();

  void setFavored(boolean favored);

  int getMinimalValue();

  boolean isCheapened();

  boolean hasState(TraitStateType... type);
}