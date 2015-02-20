package net.sf.anathema.hero.equipment;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;

public interface EquipmentOptionsProvider {
  void enableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option);

  void disableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option);

  boolean isStatOptionEnabled(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option);

  StatsOptions getEnabledStatOptions(IEquipmentItem item, IEquipmentStats stats);

  StatsOptions getEnabledStatOptions(IEquipmentStats stats);

  // returns true if options have been transferred
  boolean transferOptions(IEquipmentItem fromItem, IEquipmentItem toItem);
}
