package net.sf.anathema.equipment.editor.stats.presenter;

import static net.sf.anathema.equipment.stats.EquipmentStatisticsType.TraitModifying;
import net.sf.anathema.library.resources.RelativePath;

public class TraitModifierStatsConfiguration extends NewStatsConfiguration {
  public TraitModifierStatsConfiguration() {
    super("Equipment.Creation.Stats.AddTraitModifierTooltip", new RelativePath("icons/TraitModifying16.png"),
            "EquipmentStats.TraitModifying", TraitModifying);
  }
}