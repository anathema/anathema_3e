package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.individual.change.ChangeFlavor;
import net.sf.anathema.hero.individual.change.FlavoredChangeListener;
import net.sf.anathema.hero.individual.model.Hero;

import org.jmock.example.announcer.Announcer;

import java.util.Arrays;

import static net.sf.anathema.hero.concept.model.concept.ConceptChange.FLAVOR_CASTE;
import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;

public class TraitStateImpl implements TraitState {

  private TraitStateType currentState;
  private final Announcer<TraitStateChangedListener> stateChangeAnnouncer = Announcer.to(
          TraitStateChangedListener.class);
  private final MappableTypeIncrementChecker<TraitStateType> checker;
  private final Castes traitCastes;
  private final RequiredTraitState requiredState;
  private final TraitStateTypes types;

  public TraitStateImpl(Hero hero, Castes traitCastes, MappableTypeIncrementChecker<TraitStateType> checker,
                        RequiredTraitState requiredState, TraitStateTypes traitStateTypes) {
    this.traitCastes = traitCastes;
    this.checker = checker;
    this.requiredState = requiredState;
    this.currentState = requiredState.overrideStateIfNecessary(Default);
    this.types = traitStateTypes;
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
    int stateCount = types.size();
    for (int i = 1; i < stateCount; i++) {
      TraitStateType nextState = types.getNext(currentState, i);
      if (isLegalState(nextState)) {
        return nextState;
      }
    }
    return currentState;
  }

  private boolean isLegalState(TraitStateType newState) {
    if (!requiredState.satisfiesRequirement(newState)) {
      return false;
    }
    for (TraitStateType candidate : types) {
      if (candidate.equals(currentState)) continue;
      if (newState.countsAs(candidate) && !checker.isValidIncrement(candidate, 1)) {
        return false;
      }
    }
    return isSelectableForState(newState);
  }

  @Override
  public boolean hasState(TraitStateType... types) {
    return Arrays.asList(types).contains(getType());
  }

  @Override
  public boolean isSelectableForState(TraitStateType state) {
    return !state.countsAs(Caste) || traitCastes.isCurrentCasteSupported();
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
  public boolean isCheapened() {
    return isCheapened(currentState);
  }

  public static boolean isCheapened(TraitStateType state) {
    return !state.equals(Default);
  }

  protected void changeStateTo(TraitStateType state) {
    state = requiredState.overrideStateIfNecessary(state);
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
      }
    }

    private void clearCaste() {
      if (currentState.countsAs(Caste)) {
        changeStateTo(Default);
      }
    }
  }

  @Override
  public boolean countsTowardsLimitsOn(TraitStateType type) {
    return true;
  }
}