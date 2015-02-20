package net.sf.anathema.hero.equipment;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.ITraitModifyingStats;
import net.sf.anathema.equipment.stats.IWeaponModifiers;

public class WeaponModifiers implements IWeaponModifiers {
  private final List<ITraitModifyingStats> stats = new ArrayList<>();

  public WeaponModifiers(List<IEquipmentItem> equipmentItems) {
    for (IEquipmentItem item : equipmentItems) {
      for (IEquipmentStats equipmentStats : item.getStats()) {
        boolean isModifier = equipmentStats instanceof ITraitModifyingStats;
        boolean isPrinted = item.isPrintEnabled(equipmentStats);
        if (isModifier && isPrinted) {
          stats.add((ITraitModifyingStats) equipmentStats);
        }
      }
    }
  }

  @Override
  public int getMeleeAccuracyMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getMeleeAccuracyMod() > highest ? stat.getMeleeAccuracyMod() : highest;
    }
    return highest;
  }

  @Override
  public int getMeleeDamageMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getMeleeDamageMod() > highest ? stat.getMeleeDamageMod() : highest;
    }
    return highest;
  }

  @Override
  public int getRangedAccuracyMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getRangedAccuracyMod() > highest ? stat.getRangedAccuracyMod() : highest;
    }
    return highest;
  }

  @Override
  public int getRangedDamageMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getRangedDamageMod() > highest ? stat.getRangedDamageMod() : highest;
    }
    return highest;
  }

  @Override
  public int getPDVPoolMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getPDVPoolMod() > highest ? stat.getPDVPoolMod() : highest;
    }
    return highest;
  }
}