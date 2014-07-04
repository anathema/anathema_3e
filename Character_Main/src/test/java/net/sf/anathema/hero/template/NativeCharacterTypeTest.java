package net.sf.anathema.hero.template;

import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.template.DummyHeroSplat;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.splat.CharacterType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NativeCharacterTypeTest {

  @Test
  public void returnsNativeTypeFromTemplate() throws Exception {
    CharacterType expectedType = new DummyMundaneCharacterType();
    Hero hero = mock(Hero.class);
    when(hero.getSplat()).thenReturn(new DummyHeroSplat());
    CharacterType type = NativeCharacterType.get(hero);
    assertThat(type, is(expectedType));

  }
}
