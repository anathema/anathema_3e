package net.sf.anathema.hero.traits.model;

public class NullTraitFavorization implements ITraitFavorization {

  @Override
  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    // nothing to do
  }

  @Override
  public FavorableState getFavorableState() {
    return FavorableState.Default;
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
  public void setFavorableState(FavorableState state) {
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
}