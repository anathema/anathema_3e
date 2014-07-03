package net.sf.anathema.hero.combos;

import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.charm.data.CharmType;
import net.sf.anathema.hero.combos.model.ComboRules;
import net.sf.anathema.hero.combos.model.rules.SimpleCharmComboRules;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleCharmComboRulesTest extends AbstractComboRulesTestCase {

  private ComboRules rules = new SimpleCharmComboRules();

  @Test
  public void testCharmComboTwoSimple() {
    Charm charm1 = new DummyCharm("Instant", CharmType.Simple);
    Charm charm2 = new DummyCharm("Instant", CharmType.Simple);
    assertFalse(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testCharmComboSimpleReflexive() throws Exception {
    Charm charm1 = new DummyCharm("Instant", CharmType.Simple);
    Charm charm2 = new DummyCharm("Instant", CharmType.Reflexive);
    assertTrue(rules.isComboLegal(charm1, charm2));
  }

  @Test
  public void testCharmComboSimpleCharmWithSupplementalOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboSimpleCharmWithSupplementalOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboSimpleCharmWithSupplementalOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeSupplementalAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeSupplementalForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.Simple, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboSimpleCharmWithExtraActionOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboSimpleCharmWithExtraActionOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboSimpleCharmWithExtraActionOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeExtraActionAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }

  @Test
  public void testCharmComboAbilitySimpleCharmCombosWithAttributeExtraActionForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.Simple, CharmType.ExtraAction));
  }
}