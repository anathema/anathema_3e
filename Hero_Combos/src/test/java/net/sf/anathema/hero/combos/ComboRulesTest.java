package net.sf.anathema.hero.combos;

import net.sf.anathema.hero.combos.model.rules.AbstractComboArbitrator;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.charm.data.CharmType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComboRulesTest extends AbstractComboRulesTestCase {

  private AbstractComboArbitrator rules = new AbstractComboArbitrator() {
    @Override
    protected boolean isCharmLegalByRules(Charm charm) {
      return true;
    }
  };

  @Test
  public void testDurationComboLegal() throws Exception {
    assertTrue(rules.isCharmComboLegal(new DummyCharm("Instant", CharmType.Reflexive)));
  }

  @Test
  public void illegalCharmIsRejected() throws Exception {
    rules = new AbstractComboArbitrator() {
      @Override
      protected boolean isCharmLegalByRules(Charm charm) {
        return false;
      }
    };
    assertFalse(rules.isCharmComboLegal(new DummyCharm("Other", CharmType.Reflexive)));
  }
  @Test
  public void testCharmComboSelf() throws Exception {
    Charm charm1 = new DummyCharm("Instant", CharmType.Reflexive);
    assertFalse(rules.isComboLegal(charm1, charm1));
  }
}