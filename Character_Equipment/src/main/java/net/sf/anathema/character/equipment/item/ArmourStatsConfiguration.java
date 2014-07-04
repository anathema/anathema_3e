package net.sf.anathema.character.equipment.item;

import net.sf.anathema.library.resources.RelativePath;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Armor;

public class ArmourStatsConfiguration extends NewStatsConfiguration {
  public ArmourStatsConfiguration() {
    super("Equipment.Creation.Stats.AddArmourTooltip", new RelativePath("icons/Armor16.png"),
            "EquipmentStats.Armor", Armor);
  }
}