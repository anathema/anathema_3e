package net.sf.anathema.hero.abilities.state;

import net.sf.anathema.hero.abilities.model.FavoredMinimum;
import net.sf.anathema.hero.traits.model.Trait;
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
    when(stateMap.getType(trait)).thenReturn(TraitState.Favored);
    FavoredMinimum minimum = new FavoredMinimum(stateMap, trait);
    assertThat(minimum.getMinimum(), Matchers.is(1));
  }

  @Test
  public void hasMinimumZeroForDefaultTrait() throws Exception {
    when(stateMap.getType(trait)).thenReturn(TraitState.Default);
    FavoredMinimum minimum = new FavoredMinimum(stateMap, trait);
    assertThat(minimum.getMinimum(), Matchers.is(0));
  }
}
