package net.sf.anathema.hero.equipment.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.ITraitModifyingStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;

public class CharacterStatsModifiers implements HeroStatsModifiers {
  private final List<ITraitModifyingStats> stats = new ArrayList<>();
  private int mobilityPenalty;

  public static CharacterStatsModifiers extractFromCharacter(Hero hero) {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    return new CharacterStatsModifiers(model.getNaturalWeapons());
  }

  public CharacterStatsModifiers(Iterable<IEquipmentItem> equipmentItems) {
    for (IEquipmentItem item : equipmentItems) {
      for (IEquipmentStats equipmentStats : item.getStats()) {
        boolean isDefensive = equipmentStats instanceof IArmourStats;
        boolean isShield = equipmentStats instanceof IWeaponStats;
        boolean isModifier = equipmentStats instanceof ITraitModifyingStats;
        boolean isPrinted = item.isPrintEnabled(equipmentStats);
        if (isModifier && isPrinted) {
          stats.add((ITraitModifyingStats) equipmentStats);
        }
        if (isDefensive && isPrinted) {
          mobilityPenalty += ((IArmourStats) equipmentStats).getMobilityPenalty();
        }
        if (isShield && isPrinted) {
          mobilityPenalty += ((IWeaponStats) equipmentStats).getMobilityPenalty();
        }
      }
    }
  }

  @Override
  public int getMobilityPenalty() {
    return mobilityPenalty;
  }

  @Override
  public int getDDVPoolMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getDDVPoolMod() > highest ? stat.getDDVPoolMod() : highest;
    }
    return highest;
  }

  @Override
  public int getJoinBattleMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getJoinBattleMod() > highest ? stat.getJoinBattleMod() : highest;
    }
    return highest;
  }
}