package net.sf.anathema.equipment.template;

import net.sf.anathema.equipment.stats.IEquipmentStats;

import java.util.Collection;

public interface IEquipmentTemplate {

  String getName();

  String getDescription();

  Collection<IEquipmentStats> getStatsList();
  
  ItemCost getCost();
}