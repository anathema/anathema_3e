package net.sf.anathema.hero.traits.model;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.ConceptChange;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.change.ChangeFlavor;
import net.sf.anathema.library.change.FlavoredChangeListener;
import org.jmock.example.announcer.Announcer;

import static net.sf.anathema.hero.traits.model.FavorableState.Caste;
import static net.sf.anathema.hero.traits.model.FavorableState.Default;
import static net.sf.anathema.hero.traits.model.FavorableState.Favored;
import static net.sf.anathema.hero.traits.model.FavorableState.Supernal;

public class TraitFavorization implements ITraitFavorization {

  private FavorableState state;
  private final Announcer<IFavorableStateChangedListener> favorableStateControl = Announcer.to(IFavorableStateChangedListener.class);
  private final MappableTypeIncrementChecker<FavorableState> favoredIncrementChecker;
  private final Trait trait;
  private final CasteType[] castes;
  private final boolean isRequiredFavored;
  private final Hero hero;

  public TraitFavorization(Hero hero, CasteType[] castes, MappableTypeIncrementChecker<FavorableState> favoredIncrementChecker, Trait trait, boolean isRequiredFavored) {
    this.hero = hero;
    this.castes = castes;
    this.favoredIncrementChecker = favoredIncrementChecker;
    this.trait = trait;
    this.isRequiredFavored = isRequiredFavored;
    this.state = isRequiredFavored ? Favored : Default;
    hero.getChangeAnnouncer().addListener(new UpdateFavoredStateOnCasteChange());
  }

  @Override
  public final void setFavorableState(FavorableState state) {
    if (isRequiredFavored && state == Default) {
      state = Favored;
    }
    if (isLegalState(state)) {
      this.state = state;
      ensureMinimalValue();
      favorableStateControl.announce().favorableStateChanged(this.state);
    }
  }

  @Override
  public final void advanceFavorableState() {
    setFavorableState(getNextLegalState());
  }

  private FavorableState getNextLegalState() {
    final int stateCount = FavorableState.values().length;
    for (int i = 1; i < stateCount; i++) {
      FavorableState nextState = FavorableState.values()[(state.ordinal() + i) % FavorableState.values().length];
      if (isLegalState(nextState)) {
        return nextState;
      }
    }
    return state;
  }

  private boolean isLegalState(FavorableState state) {
    if (state == Caste && isRequiredFavored) {
      throw new IllegalStateException("Traits that are required to be favored must not be of any caste");
    }
    if (!this.state.countsAs(state) && !favoredIncrementChecker.isValidIncrement(state, 1)) {
      return false;
    }
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    if ((state == Caste || state == Supernal) && !isSupportedCasteType(casteType)) {
      return false;
    }
    return true;
  }

  private void ensureMinimalValue() {
    final int minimalValue = getMinimalValue();
    if (trait.getCurrentValue() < minimalValue) {
      trait.setCurrentValue(minimalValue);
    }
  }

  @Override
  public int getMinimalValue() {
    return this.state == Favored ? 1 : 0;
  }

  @Override
  public void setFavored(boolean favored) {
    if (isCaste() || isFavored() == favored) {
      return;
    }
    setFavorableState(favored ? Favored : Default);
  }

  @Override
  public void clearCaste() {
    if (isCaste()) {
      setFavorableState(Default);
    }
  }

  @SuppressWarnings("ConstantConditions")
  public void setCaste(boolean caste) {
    if (!caste && !isCaste()) {
      return;
    }
    setFavorableState(caste ? Caste : (isCaste() ? Default : Favored));
  }

  @Override
  public final FavorableState getFavorableState() {
    return state;
  }

  @Override
  public final void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    favorableStateControl.addListener(listener);
  }

  @Override
  public final boolean isFavored() {
    return state == Favored;
  }

  @Override
  public final boolean isCaste() {
    return state.countsAs(Caste);
  }

  @Override
  public final boolean isCasteOrFavored() {
    return isCaste() || isFavored();
  }

  private void updateFavorableStateToCaste() {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    setCaste(isSupportedCasteType(casteType));
  }

  private boolean isSupportedCasteType(CasteType casteType) {
    for (CasteType caste : castes) {
      if (caste == casteType) {
        return true;
      }
    }
    return false;
  }

  public class UpdateFavoredStateOnCasteChange implements FlavoredChangeListener {

    @Override
    public void changeOccurred(ChangeFlavor flavor) {
      if (flavor == ConceptChange.FLAVOR_CASTE) {
        clearCaste();
        updateFavorableStateToCaste();
      }
    }
  }
}