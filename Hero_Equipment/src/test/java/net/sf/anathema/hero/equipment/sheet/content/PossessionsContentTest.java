package net.sf.anathema.hero.equipment.sheet.content;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.lib.dummy.DummyResources;

import org.junit.Test;

public class PossessionsContentTest {

  @Test
  public void doesNotUseSingleLinePerItem() throws Exception {
    assertThat(new PossessionsContent(new DummyHero(), new DummyResources()).useNewLineForEachEntry(), is(false));
  }
}