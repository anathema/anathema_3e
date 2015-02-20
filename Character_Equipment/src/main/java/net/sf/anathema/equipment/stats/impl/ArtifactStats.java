package net.sf.anathema.equipment.stats.impl;

import java.util.List;

import net.sf.anathema.equipment.stats.ArtifactAttuneType;
import net.sf.anathema.equipment.stats.IArtifactStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.ItemStatsSet;

import com.google.common.collect.Lists;

public class ArtifactStats extends AbstractNonCombatStats implements IArtifactStats {
  int attuneCost;
  boolean allowForeignAttunement;
  boolean requireAttunement;

  @Override
  public Integer getAttuneCost() {
    return attuneCost;
  }

  public void setAttuneCost(int cost) {
    attuneCost = cost;
  }

  @Override
  public ArtifactAttuneType getAttuneType() {
    return ArtifactAttuneType.Attuned;
  }

  public void setAllowForeignAttunement(boolean value) {
    allowForeignAttunement = value;
  }

  public void setRequireAttunement(boolean value) {
    requireAttunement = value;
  }

  @Override
  public ItemStatsSet getViews() {
    List<IEquipmentStats> views = Lists.newArrayList();
    views.add(createAttunement(ArtifactAttuneType.Attuned));
    return new ItemStatsSet(views);
  }

  private ArtifactStatsDecorator createAttunement(ArtifactAttuneType artifactAttuneType) {
    return new ArtifactStatsDecorator(this, artifactAttuneType, requireAttunement);
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  public String toString() {
    return getId() + "[" + attuneCost + "m]";
  }
}