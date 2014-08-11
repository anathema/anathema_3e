package net.sf.anathema.hero.abilities.state;

import net.sf.anathema.hero.abilities.model.FavoredMinimum;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.FavoredTraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitState;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FavoredMinimumTest {

  private Trait trait = mock(Trait.class);
  private TraitStateMap stateMap = mock(TraitStateMap.class);

  @Test
  public void hasMinimumOneForFavoredTrait() throws Exception {
    configureState(FavoredTraitStateType.Favored);
    FavoredMinimum minimum = new FavoredMinimum(stateMap, trait);
    assertThat(minimum.getMinimum(), Matchers.is(1));
  }

  @Test
  public void hasMinimumZeroForDefaultTrait() throws Exception {
    configureState(DefaultTraitStateType.Default);
    FavoredMinimum minimum = new FavoredMinimum(stateMap, trait);
    assertThat(minimum.getMinimum(), Matchers.is(0));
  }

  private void configureState(TraitStateType type) {
    TraitState state = createState(type);
    when(stateMap.getState(trait)).thenReturn(state);
  }

  private TraitState createState(TraitStateType type) {
    TraitState state = mock(TraitState.class);
    when(state.hasState(type)).thenReturn(true);
    return state;
  }
}
