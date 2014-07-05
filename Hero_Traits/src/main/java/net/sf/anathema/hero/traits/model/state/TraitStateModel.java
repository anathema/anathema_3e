package net.sf.anathema.hero.traits.model.state;

public interface TraitStateModel {

  void addTraitStateChangedListener(TraitStateChangedListener listener);

  TraitState getType();

  void clearCaste();

  boolean isCaste();

  boolean isCasteOrFavored();

  boolean isFavored();

  void changeStateTo(TraitState state);
  
  void advanceFavorableState();

  void setFavored(boolean favored);

  int getMinimalValue();
}