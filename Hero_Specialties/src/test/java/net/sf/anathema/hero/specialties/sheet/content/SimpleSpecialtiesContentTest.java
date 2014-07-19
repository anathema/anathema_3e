package net.sf.anathema.hero.specialties.sheet.content;

import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.lib.dummy.DummyResources;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSpecialtiesContentTest {

  @Test
  public void doesNotUseSingleLinePerItem() throws Exception {
    assertThat(new SimpleSpecialtiesContent(new DummyHero(), new DummyResources()).useNewLineForEachEntry(), is(false));
  }
}