package net.sf.anathema.hero.combos;

import net.sf.anathema.charm.data.CharmType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SupplementalCharmComboRulesTest extends AbstractComboRulesTestCase {

  @Test
  public void testCharmComboSupplementalCharmWithReflexiveOfDifferentAbility() throws Exception {
    assertTrue(comboDifferentAbilityCharms(CharmType.Supplemental, CharmType.Reflexive));
  }

  @Test
  public void testCharmComboTwoSupplementalOfSameAbility() throws Exception {
    assertTrue(comboSameAbilityCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboTwoSupplementalOfDifferentAbility() throws Exception {
    assertFalse(comboDifferentAbilityCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboTwoSupplementalOfDifferentAttribute() throws Exception {
    assertTrue(comboDifferentAttributeCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilityAttributeSupplementalAllowed() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(true);
    assertTrue(comboAbilityAttributeCharms(CharmType.Supplemental, CharmType.Supplemental));
  }

  @Test
  public void testCharmComboAbilityAttributeSupplementalForbidden() throws Exception {
    getRules().setCrossPrerequisiteTypeComboAllowed(false);
    assertFalse(comboAbilityAttributeCharms(CharmType.Supplemental, CharmType.Supplemental));
  }
}