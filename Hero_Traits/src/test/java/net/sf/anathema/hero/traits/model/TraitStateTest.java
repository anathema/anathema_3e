package net.sf.anathema.hero.traits.model;

import org.junit.Ignore;
import org.junit.Test;

import static net.sf.anathema.hero.traits.model.state.TraitStateType.Caste;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Default;
import static net.sf.anathema.hero.traits.model.state.TraitStateType.Supernal;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TraitStateTest {

  @Test
  public void defaultDoesNotCountAsCaste() throws Exception {
    assertFalse(Default.countsAs(Caste));
  }

  @Test
  public void defaultCountsAsDefault() throws Exception {
    assertTrue(Default.countsAs(Default));
  }

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