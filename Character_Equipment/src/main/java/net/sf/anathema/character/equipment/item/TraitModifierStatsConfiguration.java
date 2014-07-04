package net.sf.anathema.character.equipment.item;

import net.sf.anathema.library.resources.RelativePath;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.TraitModifying;

public class TraitModifierStatsConfiguration extends NewStatsConfiguration {
  public TraitModifierStatsConfiguration() {
    super("Equipment.Creation.Stats.AddTraitModifierTooltip", new RelativePath("icons/TraitModifying16.png"),
            "EquipmentStats.TraitModifying", TraitModifying);
  }
}