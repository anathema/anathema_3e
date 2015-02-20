package net.sf.anathema.character.equipment.impl.character.model.stats;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import net.sf.anathema.equipment.stats.IWeaponModifiers;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.hero.equipment.model.ModifierFactory;
import net.sf.anathema.hero.equipment.model.ProxyWeaponStats;

import org.junit.Before;
import org.junit.Test;

public class ProxyWeaponStats_EquipmentModifierTest {
  IWeaponStats original = mock(IWeaponStats.class);
  IWeaponModifiers modifiers = mock(IWeaponModifiers.class);
  ModifierFactory factory = mock(ModifierFactory.class);
  ProxyWeaponStats stats = new ProxyWeaponStats(original, factory);

  @Before
  public void createModifiers() throws Exception {
    when(factory.createModifiers()).thenReturn(modifiers);
  }

  @Before
  public void setUpWeapon() throws Exception {
    when(original.getTags()).thenReturn(Collections.emptyList());
  }

  @Test
  public void respectsAccuracyFromEquipment() throws Exception {
    when(modifiers.getMeleeAccuracyMod()).thenReturn(5);
    int accuracy = stats.getAccuracy();
    assertThat(accuracy, is(5));
  }

  @Test
  public void respectsDamageFromEquipment() throws Exception {
    when(modifiers.getMeleeDamageMod()).thenReturn(2);
    int damage = stats.getDamage();
    assertThat(damage, is(2));
  }

  @Test
  public void respectsPDVPoolFromEquipment() throws Exception {
    when(modifiers.getPDVPoolMod()).thenReturn(2);
    int defence = stats.getDefence();
    assertThat(defence, is(2));
  }
}