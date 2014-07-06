package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.concept.model.concept.CasteType;
import net.sf.anathema.hero.concept.model.concept.HeroConceptFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.library.change.ChangeFlavor;
import net.sf.anathema.library.change.FlavoredChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.hero.concept.model.concept.ConceptChange.FLAVOR_CASTE;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Favored;

public class TraitStateImpl implements TraitState {

  private TraitStateType currentState;
  private final Announcer<TraitStateChangedListener> stateChangeAnnouncer = Announcer.to(TraitStateChangedListener.class);
  private final MappableTypeIncrementChecker<TraitStateType> checker;
  private final List<CasteType> castes;
  private final boolean isRequiredFavored;
  private final Hero hero;

  public TraitStateImpl(Hero hero, List<CasteType> castes, MappableTypeIncrementChecker<TraitStateType> checker, boolean requiredFavor) {
    this.hero = hero;
    this.castes = castes;
    this.checker = checker;
    this.isRequiredFavored = requiredFavor;
    this.currentState = requiredFavor ? Favored : Default;
    hero.getChangeAnnouncer().addListener(new UpdateStateOnCasteChange());
  }

  @Override
  public final void advanceState() {
    changeStateTo(getNextLegalState());
  }

  @Override
  public void restore(TraitStateType type) {
    this.currentState = type;
  }

  private TraitStateType getNextLegalState() {
    final int stateCount = TraitStateType.values().length;
    for (int i = 1; i < stateCount; i++) {
      TraitStateType nextState = TraitStateType.values()[(currentState.ordinal() + i) % TraitStateType.values().length];
      if (isLegalState(nextState)) {
        return nextState;
      }
    }
    return currentState;
  }

  private boolean isLegalState(TraitStateType newState) {
    if (newState == Caste && isRequiredFavored) {
      throw new IllegalStateException("Traits that are required to be favored must not be of any caste");
    }
    if (!currentState.countsAs(newState) && !checker.isValidIncrement(newState, 1)) {
      return false;
    }
    if (newState.countsAs(Caste) && !isSupportedCasteType(getCurrentCaste())) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isCheapened() {
    return !currentState.equals(Default);
  }

  @Override
  public boolean hasState(TraitStateType... types) {
    return Arrays.asList(types).contains(getType());
  }

  @Override
  public boolean isSelectableForCaste() {
    CasteType currentCaste = getCurrentCaste();
    return castes.contains(currentCaste);
  }

  @SuppressWarnings("ConstantConditions")
  public void setCaste(boolean caste) {
    if (!caste && !isCaste()) {
      return;
    }
    changeStateTo(caste ? Caste : (isCaste() ? Default : Favored));
  }

  @Override
  public final TraitStateType getType() {
    return currentState;
  }

  @Override
  public final void addTraitStateChangedListener(TraitStateChangedListener listener) {
    stateChangeAnnouncer.addListener(listener);
  }

  @Override
  public final boolean isFavored() {
    return currentState.countsAs(Favored);
  }

  @Override
  public final boolean isCaste() {
    return currentState.countsAs(Caste);
  }

  @Override
  public final boolean isCasteOrFavored() {
    return isCaste() || isFavored();
  }

  private void updateFavorableStateToCaste() {
    CasteType casteType = getCurrentCaste();
    setCaste(isSupportedCasteType(casteType));
  }

  private boolean isSupportedCasteType(CasteType casteType) {
    for (CasteType caste : castes) {
      if (caste.equals(casteType)) {
        return true;
      }
    }
    return false;
  }

  private CasteType getCurrentCaste() {
    return HeroConceptFetcher.fetch(hero).getCaste().getType();
  }

  private void changeStateTo(TraitStateType state) {
    if (isRequiredFavored && state == Default) {
      state = Favored;
    }
    if (isLegalState(state)) {
      this.currentState = state;
      stateChangeAnnouncer.announce().favorableStateChanged(this.currentState);
    }
  }

  public class UpdateStateOnCasteChange implements FlavoredChangeListener {

    @Override
    public void changeOccurred(ChangeFlavor flavor) {
      if (FLAVOR_CASTE.equals(flavor)) {
        clearCaste();
        updateFavorableStateToCaste();
      }
    }

    private void clearCaste() {
      if (isCaste()) {
        changeStateTo(Default);
      }
    }
  }
}