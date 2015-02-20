package net.sf.anathema.equipment.stats.impl;

import java.util.List;

import net.sf.anathema.equipment.stats.IArtifactStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.ItemStatsSet;

import com.google.common.collect.Lists;

public class ArtifactStats extends AbstractNonCombatStats implements IArtifactStats {
  int attuneCost;

  @Override
  public Integer getAttuneCost() {
    return attuneCost;
  }

  public void setAttuneCost(int cost) {
    attuneCost = cost;
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  public String toString() {
    return getId() + "[" + attuneCost + "m]";
  }
}