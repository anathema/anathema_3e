package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.lib.dummy.DummyResources;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PossessionsContentTest {

  @Test
  public void doesNotUseSingleLinePerItem() throws Exception {
    assertThat(new PossessionsContent(new DummyHero(), new DummyResources()).useNewLineForEachEntry(), is(false));
  }
}