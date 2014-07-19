package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.lib.dummy.DummyResources;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleIntimaciesContentTest {

  @Test
  public void doesNotUseSingleLinePerItem() throws Exception {
    assertThat(new SimpleIntimaciesContent(new DummyHero(), new DummyResources()).useNewLineForEachEntry(), is(true));
  }
}