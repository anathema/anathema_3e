package net.sf.anathema.equipment.presentation;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.IEquipmentStats;

public interface IEquipmentStringBuilder {

  String createString(IEquipmentItem item, IEquipmentStats equipment);
}