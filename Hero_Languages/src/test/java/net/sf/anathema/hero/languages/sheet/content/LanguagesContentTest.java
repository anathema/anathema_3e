package net.sf.anathema.hero.languages.sheet.content;

import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.lib.dummy.DummyResources;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LanguagesContentTest {

  @Test
  public void doesNotUseSingleLinePerItem() throws Exception {
    assertThat(new LanguagesContent(new DummyHero(), new DummyResources()).useNewLineForEachEntry(), is(false));
  }
}