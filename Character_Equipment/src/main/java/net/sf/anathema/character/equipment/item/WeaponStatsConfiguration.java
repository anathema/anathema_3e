package net.sf.anathema.character.equipment.item;

import net.sf.anathema.library.resources.RelativePath;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Weapon;

public class WeaponStatsConfiguration extends NewStatsConfiguration {
  public WeaponStatsConfiguration() {
    super("Equipment.Creation.Stats.AddWeaponTooltip", new RelativePath("icons/CloseCombat16.png"),
            "EquipmentStats.Weapon", Weapon);
  }
}