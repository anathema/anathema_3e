package net.sf.anathema.equipment.stats.impl;

import net.sf.anathema.equipment.stats.IArtifactStats;

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