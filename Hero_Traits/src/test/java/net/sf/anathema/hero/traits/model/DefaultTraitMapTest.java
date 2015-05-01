package net.sf.anathema.hero.traits.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultTraitMapTest {

  @Test
  public void createsNullTraitForUnfullfillableRequests() {
    DefaultTraitMap map = new DefaultTraitMap();
    TraitType type = new TraitType("Test");
    Trait test = map.getTrait(type);
    test.setCurrentValue(5);
    assertThat(test.getCurrentValue(), is(0));
  }

}