package net.sf.anathema.equipment.stats.impl;

import net.sf.anathema.equipment.stats.ArtifactAttuneType;
import net.sf.anathema.equipment.stats.IArtifactStats;
import net.sf.anathema.equipment.stats.ItemStatsSet;

public class ArtifactStatsDecorator extends AbstractStats implements IArtifactStats {
  private ArtifactStats stats;
  private ArtifactAttuneType type;

  public ArtifactStatsDecorator(ArtifactStats stats, ArtifactAttuneType type, boolean requireAttune) {
    this.stats = stats;
    this.type = type;
    setName(stats.getName());
  }

  @Override
  public Integer getAttuneCost() {
    switch (type) {
      default:
      case Unattuned:
        return 0;
      case Attuned:
        return stats.getAttuneCost();
    }
  }

  @Override
  public ArtifactAttuneType getAttuneType() {
    return type;
  }

  @Override
  public ItemStatsSet getViews() {
    return ItemStatsSet.withSingleStat(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ArtifactStatsDecorator)) {
      return false;
    }
    ArtifactStatsDecorator view = (ArtifactStatsDecorator) obj;
    return view.stats.equals(stats) && view.type == type;
  }

  public String toString() {
    return stats.toString() + "[" + type + "]";
  }

  @Override
  public int hashCode() {
    return stats.hashCode();
  }

  @Override
  public String getId() {
    return getName().getId() + "." + type.name();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return stats.representsItemForUseInCombat();
  }
}
