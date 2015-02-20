package net.sf.anathema.equipment.core;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;

public interface IEquipmentStringBuilder {

  String createString(IEquipmentItem item, IEquipmentStats equipment);
}