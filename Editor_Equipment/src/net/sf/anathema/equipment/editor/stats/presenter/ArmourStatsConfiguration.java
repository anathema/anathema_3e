package net.sf.anathema.equipment.editor.stats.presenter;

import net.sf.anathema.library.resources.RelativePath;

import static net.sf.anathema.equipment.editor.stats.model.EquipmentStatisticsType.Armor;

public class ArmourStatsConfiguration extends NewStatsConfiguration {
  public ArmourStatsConfiguration() {
    super("Equipment.Creation.Stats.AddArmourTooltip", new RelativePath("icons/Armor16.png"),
            "EquipmentStats.Armor", Armor);
  }
}