package net.sf.anathema.hero.traits.model;

import org.junit.Ignore;
import org.junit.Test;

import static net.sf.anathema.hero.traits.model.FavorableState.Caste;
import static net.sf.anathema.hero.traits.model.FavorableState.Supernal;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FavorableStateTest {

  @Test
  public void supernalCountsAsCaste() throws Exception {
    assertTrue(Supernal.countsAs(Caste));
  }

  @Ignore
  @Test
  public void casteDoesNotCountAsSupernal() throws Exception {
    assertFalse(Caste.countsAs(Supernal));
  }
}