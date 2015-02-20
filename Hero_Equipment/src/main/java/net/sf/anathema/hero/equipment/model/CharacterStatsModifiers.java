package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.stats.HeroStatsModifiers;

public class CharacterStatsModifiers implements HeroStatsModifiers {
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
        boolean isPrinted = item.isPrintEnabled(equipmentStats);
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
}