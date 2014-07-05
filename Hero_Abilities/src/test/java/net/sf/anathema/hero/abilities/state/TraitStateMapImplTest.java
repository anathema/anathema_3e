package net.sf.anathema.hero.abilities.state;

import net.sf.anathema.hero.abilities.model.FavoredMinimum;
import net.sf.anathema.hero.abilities.model.TraitStateMapImpl;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.model.TraitImpl;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelImpl;
import net.sf.anathema.hero.traits.model.rules.minimum.DynamicMinimumMap;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static net.sf.anathema.hero.traits.model.types.AbilityType.Archery;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TraitStateMapImplTest {

  public static final AbilityType ANY_TYPE = Archery;
  private Hero hero;
  private TraitStateMapImpl stateMap;
  private TraitImpl trait;
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
    stateMap.addTrait(trait);
    verify(minimumMap).addMinimum(eq(Archery), Mockito.isA(FavoredMinimum.class));
  }
}
