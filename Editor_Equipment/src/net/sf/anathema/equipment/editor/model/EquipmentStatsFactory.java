package net.sf.anathema.equipment.editor.model;

import java.util.Collection;

import net.sf.anathema.equipment.editor.stats.model.EquipmentStatisticsType;
import net.sf.anathema.equipment.stats.IEquipmentStats;

public interface EquipmentStatsFactory {

  IEquipmentStats createNewStats(Collection<String> definedNames, String nameProposal, EquipmentStatisticsType type);
}
