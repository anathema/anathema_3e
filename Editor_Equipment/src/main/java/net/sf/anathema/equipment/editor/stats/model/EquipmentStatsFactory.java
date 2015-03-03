package net.sf.anathema.equipment.editor.stats.model;

import net.sf.anathema.equipment.stats.IEquipmentStats;

import java.util.Collection;

public interface EquipmentStatsFactory {

  IEquipmentStats createNewStats(Collection<String> definedNames, String nameProposal, EquipmentStatisticsType type);
}
