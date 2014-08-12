package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.individual.change.ChangeFlavor;
import net.sf.anathema.hero.individual.change.FlavoredChangeListener;
import net.sf.anathema.hero.individual.model.Hero;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;

import static net.sf.anathema.hero.concept.model.concept.ConceptChange.FLAVOR_CASTE;
import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;

public class TraitStateImpl implements TraitState {

  private TraitStateType currentState;
  private final Announcer<TraitStateChangedListener> stateChangeAnnouncer = Announcer.to(
          TraitStateChangedListener.class);
  private final MappableTypeIncrementChecker<TraitStateType> checker;
  private final Castes traitCastes;
  private final CasteChangedBehavior casteChangedBehavior;
  private final RequiredTraitState requiredState;

  public TraitStateImpl(Hero hero, Castes traitCastes, MappableTypeIncrementChecker<TraitStateType> checker,
                        CasteChangedBehavior casteChangedBehavior, RequiredTraitState requiredState) {
    this.traitCastes = traitCastes;
    this.checker = checker;
    this.requiredState = requiredState;
    this.currentState = requiredState.overrideStateIfNecessary(Default);
    this.casteChangedBehavior = casteChangedBehavior;
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
    TraitStateTypes types = new TraitStateTypes();
    final int stateCount = types.size();
    for (int i = 1; i < stateCount; i++) {
      TraitStateType nextState = types.getNext(currentState, i);
      if (isLegalState(nextState)) {
        return nextState;
      }
    }
    return currentState;
  }

  @SuppressWarnings("RedundantIfStatement")
  private boolean isLegalState(TraitStateType newState) {
    if (!requiredState.satisfiesRequirement(newState)) {
      return false;
    }
    if (!currentState.countsAs(newState) && !checker.isValidIncrement(newState, 1)) {
      return false;
    }
    if (newState.countsAs(Caste) && !traitCastes.isCurrentCasteSupported()) {
      return false;
    }
    return true;
  }

  @Override
  public boolean hasState(TraitStateType... types) {
    return Arrays.asList(types).contains(getType());
  }

  @Override
  public boolean isSelectableForCaste() {
    return traitCastes.isCurrentCasteSupported();
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
  public boolean isCheapened() {
    return isCheapened(currentState);
  }

  public static boolean isCheapened(TraitStateType state) {
    return !state.equals(Default);
  }

  private boolean isCaste() {
    return currentState.countsAs(Caste);
  }

  private void updateFavorableStateToCaste() {
    setCaste(traitCastes.isCurrentCasteSupported());
  }

  private void changeStateTo(TraitStateType state) {
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
        switch (casteChangedBehavior) {
          case CLEAR:
            clearCaste();
            break;
          case SET:
            updateFavorableStateToCaste();
            break;
        }

      }
    }

    private void clearCaste() {
      if (isCaste()) {
        changeStateTo(Default);
      }
    }
  }
}