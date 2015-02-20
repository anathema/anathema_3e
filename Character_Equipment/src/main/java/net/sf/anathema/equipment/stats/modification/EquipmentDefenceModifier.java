package net.sf.anathema.equipment.stats.modification;

import net.sf.anathema.equipment.stats.IWeaponModifiers;

public class EquipmentDefenceModifier implements StatModifier {

  private final IWeaponModifiers modifiers;

  public EquipmentDefenceModifier(IWeaponModifiers modifiers) {
    this.modifiers = modifiers;
  }

  @Override
  public int calculate() {
    return modifiers.getPDVPoolMod();
  }
}