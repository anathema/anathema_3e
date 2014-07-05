package net.sf.anathema.hero.individual.model;

import net.sf.anathema.hero.dummy.DummyHeroSplat;
import net.sf.anathema.hero.dummy.DummyMundaneHeroType;
import net.sf.anathema.hero.individual.splat.HeroType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NativeHeroTypeTest {

  @Test
  public void returnsNativeTypeFromTemplate() throws Exception {
    HeroType expectedType = new DummyMundaneHeroType();
    Hero hero = mock(Hero.class);
    when(hero.getSplat()).thenReturn(new DummyHeroSplat());
    HeroType type = NativeCharacterType.get(hero);
    assertThat(type, is(expectedType));

  }
}
