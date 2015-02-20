package net.sf.anathema.equipment.stats.modification;

import net.sf.anathema.equipment.stats.IWeaponModifiers;

public class EquipmentDamageModifier implements StatModifier {
  private final IWeaponModifiers modifiers;
  private final WeaponStatsType type;

  public EquipmentDamageModifier(IWeaponModifiers modifiers, WeaponStatsType type) {
    this.modifiers = modifiers;
    this.type = type;
  }

  @Override
  public int calculate() {
    if (type.isRanged()) {
      return modifiers.getRangedDamageMod();
    }
    return modifiers.getMeleeDamageMod();
  }
}