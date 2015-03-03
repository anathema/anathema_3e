package net.sf.anathema.hero.application;

import org.junit.Test;

import com.google.common.collect.Iterables;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleCharacterTemplateCacheTest {

  @Test
  public void returnsNoTemplatesWhenNoneAreRegistered() throws Exception {
    SimpleCharacterTemplateCache cache = new SimpleCharacterTemplateCache();
    assertThat(Iterables.size(cache), is(0));
  }
}
