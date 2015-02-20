package net.sf.anathema.equipment.editor.stats.presenter;

import net.sf.anathema.library.resources.RelativePath;

import static net.sf.anathema.equipment.editor.stats.model.EquipmentStatisticsType.Weapon;

public class WeaponStatsConfiguration extends NewStatsConfiguration {
  public WeaponStatsConfiguration() {
    super("Equipment.Creation.Stats.AddWeaponTooltip", new RelativePath("icons/CloseCombat16.png"),
            "EquipmentStats.Weapon", Weapon);
  }
}