package net.sf.anathema.character.equipment.view;

import net.sf.anathema.character.equipment.character.IEquipmentStringBuilder;
import net.sf.anathema.character.equipment.dummy.DemoMeleeWeapon;
import net.sf.anathema.character.equipment.dummy.DemoNaturalArmour;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentStringBuilder;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.lib.dummy.DummyResources;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.equipment.creation.model.WeaponTag.*;
import static org.junit.Assert.assertEquals;

public class EquipmentStringBuilderTest {

  private IEquipmentStringBuilder equipmentStringBuilder;

  @Before
  public void setUp() throws Exception {
    DummyResources resources = new DummyResources();
    resources.putString("Equipment.Stats.Short.Speed", "Speed");
    resources.putString("Equipment.Stats.Short.Accuracy", "Acc");
    resources.putString("Equipment.Stats.Short.Damage", "Dam");
    resources.putString("Equipment.Stats.Short.Range", "Range");
    resources.putString("Equipment.Stats.Short.Rate", "Rate");
    resources.putString("Equipment.Stats.Short.Defence", "Def");
    resources.putString("Equipment.Stats.Short.Soak", "Soak(B/L/A)");
    resources.putString("Equipment.Stats.Short.Hardness", "Hardness(B/L/A)");
    resources.putString("HealthType.Bashing.Short", "B");
    resources.putString("HealthType.Lethal.Short", "L");
    resources.putString("HealthType.Aggravated.Short", "A");
    resources.putString("Melee", "Melee");
    resources.putString("MartialArts", "Martial Arts");
    equipmentStringBuilder = new EquipmentStringBuilder(resources);
  }

  @Test
  public void testMeleeWeapon() {
    DemoMeleeWeapon weapon = new DemoMeleeWeapon(new SimpleIdentifier("Sword"), 0, 0, HealthType.Lethal, 0, 0, Light, Lethal, Artifact);
    assertEquals("Sword (Melee): Light, Lethal, Artifact", equipmentStringBuilder.createString(null, weapon));
  }

  @Test
  public void testNaturalArmour() throws Exception {
    DemoNaturalArmour armour = new DemoNaturalArmour(new SimpleIdentifier("Natural"), 5, 2);
    String result = equipmentStringBuilder.createString(null, armour);
    assertEquals("Natural: Soak(B/L/A):+5/+2/- Hardness(B/L/A):-/-", result);
  }
}