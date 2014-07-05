package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.ConceptChange;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.change.ChangeFlavor;
import net.sf.anathema.library.change.FlavoredChangeListener;
import org.jmock.example.announcer.Announcer;

import static net.sf.anathema.hero.traits.model.state.TraitState.Caste;
import static net.sf.anathema.hero.traits.model.state.TraitState.Default;
import static net.sf.anathema.hero.traits.model.state.TraitState.Favored;
import static net.sf.anathema.hero.traits.model.state.TraitState.Supernal;

public class TraitStateModelImpl implements TraitStateModel {

  private TraitState state;
  private final Announcer<TraitStateChangedListener> favorableStateControl = Announcer.to(TraitStateChangedListener.class);
  private final MappableTypeIncrementChecker<TraitState> favoredIncrementChecker;
  private final Trait trait;
  private final CasteType[] castes;
  private final boolean isRequiredFavored;
  private final Hero hero;

  public TraitStateModelImpl(Hero hero, CasteType[] castes,
                             MappableTypeIncrementChecker<TraitState> favoredIncrementChecker, Trait trait,
                             boolean isRequiredFavored) {
    this.hero = hero;
    this.castes = castes;
    this.favoredIncrementChecker = favoredIncrementChecker;
    this.trait = trait;
    this.isRequiredFavored = isRequiredFavored;
    this.state = isRequiredFavored ? Favored : Default;
    hero.getChangeAnnouncer().addListener(new UpdateFavoredStateOnCasteChange());
  }

  @Override
  public final void changeStateTo(TraitState state) {
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
    changeStateTo(getNextLegalState());
  }

  private TraitState getNextLegalState() {
    final int stateCount = TraitState.values().length;
    for (int i = 1; i < stateCount; i++) {
      TraitState nextState = TraitState.values()[(state.ordinal() + i) % TraitState.values().length];
      if (isLegalState(nextState)) {
        return nextState;
      }
    }
    return state;
  }

  private boolean isLegalState(TraitState state) {
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
    changeStateTo(favored ? Favored : Default);
  }

  @Override
  public void clearCaste() {
    if (isCaste()) {
      changeStateTo(Default);
    }
  }

  @SuppressWarnings("ConstantConditions")
  public void setCaste(boolean caste) {
    if (!caste && !isCaste()) {
      return;
    }
    changeStateTo(caste ? Caste : (isCaste() ? Default : Favored));
  }

  @Override
  public final TraitState getType() {
    return state;
  }

  @Override
  public final void addTraitStateChangedListener(TraitStateChangedListener listener) {
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