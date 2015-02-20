package net.sf.anathema.equipment.character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.equipment.stats.IEquipmentStats;

public class EquipmentTemplate implements IEquipmentTemplate {

  private final List<IEquipmentStats> statsList = new ArrayList<>();
  private final String description;
  private final String name;
  private final ItemCost cost;

  public EquipmentTemplate(String name, String description, ItemCost cost) {
    this.name = name;
    this.description = description;
    this.cost = cost;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Collection<IEquipmentStats> getStatsList() {
    return statsList;
  }

  public synchronized void addStats(IEquipmentStats stats) {
    this.statsList.add(stats);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ItemCost getCost() {
    return cost;
  }
}