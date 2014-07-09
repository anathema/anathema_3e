package net.sf.anathema.hero.combos;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.CharmType;
import net.sf.anathema.hero.charms.dummy.DummyCharm;
import net.sf.anathema.hero.combos.model.ComboImpl;
import net.sf.anathema.hero.combos.model.rules.DefaultComboArbitrator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComboRulesTest extends AbstractComboRulesTestCase {

  private DefaultComboArbitrator rules = new DefaultComboArbitrator();

  @Test
  public void rejectsPermanent() throws Exception {
    assertRejects(CharmType.Permanent);
  }

  @Test
  public void acceptsSimple() throws Exception {
    assertAccepts(CharmType.Simple);
  }

  @Test
  public void acceptsSupplemental() throws Exception {
    assertAccepts(CharmType.Supplemental);
  }

  @Test
  public void acceptsReflexive() throws Exception {
    assertAccepts(CharmType.Reflexive);
  }
  @Test
  public void rejectsComboToSelf() throws Exception {
    Charm charm1 = new DummyCharm("Instant", CharmType.Reflexive);
    assertFalse(rules.isComboLegal(charm1, charm1));
  }

  private void assertAccepts(CharmType charmType) {
    assertTrue(rules.canBeAddedToCombo(new ComboImpl(), new DummyCharm("Any", charmType)));
  }

  private void assertRejects(CharmType charmType) {
    assertFalse(rules.canBeAddedToCombo(new ComboImpl(), new DummyCharm("Any", charmType)));
  }
}