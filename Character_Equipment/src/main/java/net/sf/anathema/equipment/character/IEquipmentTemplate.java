package net.sf.anathema.equipment.character;

import java.util.Collection;

import net.sf.anathema.equipment.stats.IEquipmentStats;

public interface IEquipmentTemplate {

  String getName();

  String getDescription();

  Collection<IEquipmentStats> getStatsList();
  
  ItemCost getCost();
}