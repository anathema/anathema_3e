package net.sf.anathema.hero.traits.model.state;

import net.sf.anathema.hero.dummy.DummyHero;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.DefaultTraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.FavoredTraitStateType.Favored;
import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TraitStateImplTest {
  Castes traitCastes = mock(Castes.class);
  MappableTypeIncrementChecker<TraitStateType> checker = mock(MappableTypeIncrementChecker.class);
  TraitStateImpl state = new TraitStateImpl(new DummyHero(), traitCastes, checker, new NoRequiredState(), TraitStateTypes.withAllKnown());

  @Before
  public void supportAllCastes() throws Exception {
    when(traitCastes.isCurrentCasteSupported()).thenReturn(true);
  }

  @Test
  public void startsOutDefault() throws Exception {
    assertThat(state.getType(), is(Default));
  }

  @Test
  public void advancesToNextState() throws Exception {
    allowToSwitchToState(Favored);
    state.advanceState();
    assertThat(state.getType(), is(Favored));
  }

  @Test
  public void advancesPastForbiddenStates() throws Exception {
    forbidToSwitchToState(Favored);
    allowToSwitchToState(Caste);
    state.advanceState();
    assertThat(state.getType(), is(Caste));
  }

  @Test
  public void canAdvanceToAStateThatCountsAsTheCurrentState() throws Exception {
    forbidToSwitchToState(Favored);
    allowToSwitchToState(Caste);
    state.advanceState();
    forbidToSwitchToState(Caste);
    allowToSwitchToState(Supernal);
    state.advanceState();
    assertThat(state.getType(), is(Supernal));
  }

  @Test
  public void doesNotAdvanceToStateThatCountsAsAnotherStateWhoseLimitIsReached() throws Exception {
    forbidToSwitchToState(Favored);
    forbidToSwitchToState(Caste);
    allowToSwitchToState(Supernal);
    state.advanceState();
    assertThat(state.getType(), is(Default));
  }

  private void forbidToSwitchToState(TraitStateType state) {
    when(checker.isValidIncrement(state, 1)).thenReturn(false);
  }

  private void allowToSwitchToState(TraitStateType state) {
    when(checker.isValidIncrement(state, 1)).thenReturn(true);
  }
}