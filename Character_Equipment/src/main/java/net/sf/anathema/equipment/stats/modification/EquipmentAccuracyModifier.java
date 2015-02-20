package net.sf.anathema.equipment.stats.modification;

import net.sf.anathema.equipment.stats.IWeaponModifiers;

public class EquipmentAccuracyModifier implements StatModifier {
  private final IWeaponModifiers modifiers;
  private final WeaponStatsType type;

  public EquipmentAccuracyModifier(IWeaponModifiers modifiers, WeaponStatsType type) {
    this.modifiers = modifiers;
    this.type = type;
  }

  @Override
  public int calculate() {
    if (type.isRanged()) {
      return modifiers.getRangedAccuracyMod();
    }
    return modifiers.getMeleeAccuracyMod();
  }
}