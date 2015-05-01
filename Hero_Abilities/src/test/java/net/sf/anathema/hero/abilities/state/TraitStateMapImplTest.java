package net.sf.anathema.hero.abilities.state;

import net.sf.anathema.hero.abilities.model.FavoredMinimum;
import net.sf.anathema.hero.abilities.model.TraitStateMapImpl;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelImpl;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimumMap;
import net.sf.anathema.hero.traits.model.state.DefaultTraitStateType;
import net.sf.anathema.hero.traits.model.state.TraitState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Archery;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TraitStateMapImplTest {

  public static final TraitType ANY_TYPE = Archery;
  private Hero hero;
  private TraitStateMapImpl stateMap;
  private Trait trait;
  private DynamicMinimumMap minimumMap;
  private TraitModel traitModel;

  @Before
  public void createStateMap() throws Exception {
    hero = mock(Hero.class);
    stateMap = new TraitStateMapImpl(hero);
  }

  @Before
  public void createTraitModelWithMinimumMap() throws Exception {
    traitModel = mock(TraitModelImpl.class);
    minimumMap = mock(DynamicMinimumMap.class);
    when(traitModel.getMinimumMap()).thenReturn(minimumMap);
  }

  @Before
  public void createTraitOfAnyType() throws Exception {
    trait = mock(TraitImpl.class);
    when(trait.getType()).thenReturn(ANY_TYPE);
  }

  @Test
  public void addsFavoredMinimumToTraitModel() throws Exception {
    when(hero.getModel(TraitModel.ID)).thenReturn(traitModel);
    stateMap.addState(trait, null);
    verify(minimumMap).addMinimum(eq(Archery), Mockito.isA(FavoredMinimum.class));
  }

  @Test
  public void returnsDefaultStateWhenTraitIsUnknown() throws Exception {
    TraitState state = stateMap.getState(new TraitType("test"));
    assertThat(state.getType(), is(DefaultTraitStateType.Default));
  }
}
