package net.sf.anathema.hero.combos;

import net.sf.anathema.hero.combos.model.ComboImpl;
import net.sf.anathema.hero.combos.model.rules.AbstractComboArbitrator;
import net.sf.anathema.hero.dummy.DummyCharm;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.TraitPrerequisite;
import net.sf.anathema.charm.data.CharmType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import org.junit.Test;

import static net.sf.anathema.hero.magic.charm.duration.Duration.INSTANT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComboTest {

  private ComboImpl combo = new ComboImpl();
  private AbstractComboArbitrator comboRules = new AbstractComboArbitrator() {

    @Override
    protected boolean isCharmLegalByRules(Charm charm) {
      return charm.getDuration().text.equals(INSTANT);
    }
  };

  protected final void addCharm(Charm charm) {
    if (comboRules.canBeAddedToCombo(combo, charm)) {
      combo.addCharm(charm, false);
    } else {
      throw new IllegalArgumentException("The charm " + charm.getName().text + " is illegal in this combo.");
    }
  }

  @Test
  public void testCreation() throws Exception {
    assertEquals(0, combo.getCharms().length);
    assertTrue(combo.getName().getText().isEmpty());
    assertTrue(combo.getDescription().getText().isEmpty());
  }

  @Test
  public void testAddedCharmIsIllegal() throws Exception {
    Charm charm = new DummyCharm("Instant", CharmType.Reflexive);
    addCharm(charm);
    assertFalse(comboRules.canBeAddedToCombo(combo, charm));
  }

  @Test
  public void testOnlyInstantDurationCombos() throws Exception {
    final Charm dummy1 = new DummyCharm("Instant", CharmType.Reflexive);
    assertTrue(comboRules.canBeAddedToCombo(combo, dummy1));
    final Charm dummy2 = new DummyCharm("Other", CharmType.Reflexive);
    assertFalse(comboRules.canBeAddedToCombo(combo, dummy2));
  }

  @Test
  public void testOnlyOneExtraActionCharm() {
    Charm extraActionCharm = new DummyCharm("Instant", CharmType.ExtraAction,
      TraitPrerequisite.Create(AbilityType.Archery, 3));
    assertTrue(comboRules.canBeAddedToCombo(combo, extraActionCharm));
    addCharm(extraActionCharm);
    assertFalse(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Archery, 3))));
  }

  @Test
  public void testOnlyOneSimpleCharm() {
    Charm simpleCharm = new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AbilityType.Archery, 3));
    assertTrue(comboRules.canBeAddedToCombo(combo, simpleCharm));
    addCharm(simpleCharm);
    assertFalse(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AbilityType.Archery, 3))));
  }

  @Test
  public void testSimpleCharmOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AbilityType.Archery, 3))));
    assertFalse(comboRules.canBeAddedToCombo(combo,
        new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AbilityType.Athletics, 3)))
    );
  }

  @Test
  public void testAttributeSimpleCharmsCombosWithAbilityExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
        new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AttributeType.Appearance, 3)))
    );
  }

  @Test
  public void testAbilitySimpleCharmCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Appearance, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AbilityType.Archery, 3))));
  }

  @Test
  public void testExtraActionCharmOfSamePrimaryPrerequisiteAsSimple() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
        new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Archery, 3)))
    );
    assertFalse(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAbilitySimpleCharm() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testExtraActionOfSamePrimaryPrerequisiteAsSupplemental() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
        new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Archery, 3)))
    );
    assertFalse(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAbilitySupplemental() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Perception, 3))));
  }

  @Test
  public void testSupplementalOfSamePrimaryPrerequisiteAsExtraAction() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AbilityType.Archery, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AbilityType.Archery, 3))));
    assertFalse(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AbilityType.Athletics, 3))));
  }

  @Test
  public void testAbilitySupplementalCombosWithAttributeExtraAction() throws Exception {
    comboRules.setCrossPrerequisiteTypeComboAllowed(true);
    addCharm(new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AbilityType.Awareness, 3))));
  }

  @Test
  public void testAttributeSimpleCombosWithAttributeExtraAction() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
        new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AttributeType.Strength, 3)))
    );
  }

  @Test
  public void testAttributeExtraActionCombosWithAttributeSimple() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeExtraActionCombosWithAttributeSupplemental() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Dexterity, 3))));
  }

  @Test
  public void testAttributeSupplementalCombosWithAttributeExtraAction() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.ExtraAction, TraitPrerequisite.Create(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AttributeType.Strength, 3))));
  }

  @Test
  public void testAttributeSimpleCombosWithAttributeSupplemental() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AttributeType.Strength, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
        new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AttributeType.Dexterity, 3)))
    );
  }

  @Test
  public void testAttributeSupplementalCombosWithAttributeSimple() throws Exception {
    addCharm(new DummyCharm("Instant", CharmType.Simple, TraitPrerequisite.Create(AttributeType.Wits, 3)));
    assertTrue(comboRules.canBeAddedToCombo(combo,
      new DummyCharm("Instant", CharmType.Supplemental, TraitPrerequisite.Create(AttributeType.Strength, 3))));
  }

  @Test
  public void canCloneCombos() throws Exception {
    combo.setId(1);
    combo.clone();
  }
}