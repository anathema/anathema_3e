package net.sf.anathema.hero.traits.model;

import org.junit.Ignore;
import org.junit.Test;

import static net.sf.anathema.hero.traits.model.state.TraitState.Caste;
import static net.sf.anathema.hero.traits.model.state.TraitState.Supernal;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TraitStateTest {

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