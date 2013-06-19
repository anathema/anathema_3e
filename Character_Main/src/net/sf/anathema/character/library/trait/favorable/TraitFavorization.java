package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import org.jmock.example.announcer.Announcer;

public class TraitFavorization implements ITraitFavorization {

  private FavorableState state;
  private final Announcer<IFavorableStateChangedListener> favorableStateControl = Announcer.to(IFavorableStateChangedListener.class);
  private final IncrementChecker favoredIncrementChecker;
  private final Trait trait;
  private final CasteType[] castes;
  private final boolean isRequiredFavored;
  private final Hero hero;

  public TraitFavorization(Hero hero, CasteType[] castes, IncrementChecker favoredIncrementChecker, Trait trait, boolean isRequiredFavored) {
    this.hero = hero;
    this.castes = castes;
    this.favoredIncrementChecker = favoredIncrementChecker;
    this.trait = trait;
    this.isRequiredFavored = isRequiredFavored;
    this.state = isRequiredFavored ? FavorableState.Favored : FavorableState.Default;
  }

  @Override
  public final void setFavorableState(FavorableState state) {
    if (state == FavorableState.Caste && isRequiredFavored) {
      throw new IllegalStateException("Traits with required favored must not  be of any caste");
    }
    if (this.state == state && state != FavorableState.Caste) {
      return;
    }
    if (this.state == FavorableState.Caste && state == FavorableState.Favored) {
      return;
    }
    if (state == FavorableState.Favored && !favoredIncrementChecker.isValidIncrement(1)) {
      return;
    }
    if (isRequiredFavored && state == FavorableState.Default) {
      state = FavorableState.Favored;
    }
    this.state = state;
    ensureMinimalValue();
    fireFavorableStateChangedEvent();
  }

  @Override
  public void ensureMinimalValue() {
    final int minimalValue = getMinimalValue();
    if (trait.getCurrentValue() < minimalValue) {
      trait.setCurrentValue(minimalValue);
    }
  }

  @Override
  public int getMinimalValue() {
    return this.state == FavorableState.Favored ? 1 : 0;
  }

  @Override
  public void setFavored(boolean favored) {
    if (isCaste() || isFavored() == favored) {
      return;
    }
    setFavorableState(favored ? FavorableState.Favored : FavorableState.Default);
  }

  public void setCaste(boolean caste) {
    if (!caste && isCaste() == caste) {
      return;
    }
    setFavorableState(caste ? FavorableState.Caste : (isCaste() ? FavorableState.Default : FavorableState.Favored));
  }

  @Override
  public final FavorableState getFavorableState() {
    return state;
  }

  @Override
  public final void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    favorableStateControl.addListener(listener);
  }

  private void fireFavorableStateChangedEvent() {
    favorableStateControl.announce().favorableStateChanged(state);
  }

  @Override
  public final boolean isFavored() {
    return state == FavorableState.Favored;
  }

  @Override
  public final boolean isCaste() {
    return state == FavorableState.Caste;
  }

  @Override
  public final boolean isCasteOrFavored() {
    return isCaste() || isFavored();
  }

  @Override
  public CasteType[] getCastes() {
    return castes;
  }

  @Override
  public void updateFavorableStateToCaste() {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    setCaste(isSupportedCasteType(casteType));
  }

  private boolean isSupportedCasteType(CasteType casteType) {
    CasteType[] favorizationCaste = getCastes();
    for (CasteType caste : favorizationCaste) {
      if (caste == casteType) {
        return true;
      }
    }
    return false;
  }
}