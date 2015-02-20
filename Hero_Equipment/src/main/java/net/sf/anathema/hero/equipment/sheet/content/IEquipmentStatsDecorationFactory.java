package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;

public interface IEquipmentStatsDecorationFactory<K extends IEquipmentStats> {

  K createRenamedPrintDecoration(IEquipmentItem item, K stats);

}