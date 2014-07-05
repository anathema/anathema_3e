package net.sf.anathema.hero.traits.model.state;

public class NullTraitStateModel implements TraitStateModel {

  @Override
  public void addTraitStateChangedListener(TraitStateChangedListener listener) {
    // nothing to do
  }

  @Override
  public TraitState getType() {
    return TraitState.Default;
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
  public void changeStateTo(TraitState state) {
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