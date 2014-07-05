package net.sf.anathema.hero.traits.model;

public interface ITraitFavorization {

  void addFavorableStateChangedListener(IFavorableStateChangedListener listener);

  FavorableState getFavorableState();

  void clearCaste();

  boolean isCaste();

  boolean isCasteOrFavored();

  boolean isFavored();

  void setFavorableState(FavorableState state);
  
  void advanceFavorableState();

  void setFavored(boolean favored);

  int getMinimalValue();
}