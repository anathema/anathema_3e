package net.sf.anathema.equipment.editor.stats.presenter;

import static net.sf.anathema.equipment.stats.EquipmentStatisticsType.Weapon;
import net.sf.anathema.library.resources.RelativePath;

public class WeaponStatsConfiguration extends NewStatsConfiguration {
  public WeaponStatsConfiguration() {
    super("Equipment.Creation.Stats.AddWeaponTooltip", new RelativePath("icons/CloseCombat16.png"),
            "EquipmentStats.Weapon", Weapon);
  }
}