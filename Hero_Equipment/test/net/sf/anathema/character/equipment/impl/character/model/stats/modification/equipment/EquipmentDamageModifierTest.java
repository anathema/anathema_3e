package net.sf.anathema.character.equipment.impl.character.model.stats.modification.equipment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.sf.anathema.character.equipment.character.model.stats.modification.equipment.EquipmentDamageModifier;
import net.sf.anathema.equipment.stats.IWeaponModifiers;
import net.sf.anathema.equipment.stats.modification.WeaponStatsType;

import org.junit.Test;

public class EquipmentDamageModifierTest {
  IWeaponModifiers modifiers = mock(IWeaponModifiers.class);

  @Test
  public void returnsDamageModifierForMelee() throws Exception {
    when(modifiers.getMeleeDamageMod()).thenReturn(5);
    EquipmentDamageModifier modifier = new EquipmentDamageModifier(modifiers, WeaponStatsType.Melee);
    assertThat(modifier.calculate(), is(5));
  }

  @Test
  public void returnsDamageModifierForRanged() throws Exception {
    when(modifiers.getRangedDamageMod()).thenReturn(4);
    EquipmentDamageModifier modifier = new EquipmentDamageModifier(modifiers, WeaponStatsType.Bow);
    assertThat(modifier.calculate(), is(4));
  }
}
