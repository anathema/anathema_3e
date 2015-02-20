package net.sf.anathema.equipment.stats.impl;

import net.sf.anathema.equipment.stats.IArtifactStats;

public class ArtifactStatsDecorator extends AbstractStats implements IArtifactStats {
  private ArtifactStats stats;

  public ArtifactStatsDecorator(ArtifactStats stats) {
    this.stats = stats;
    setName(stats.getName());
  }

  @Override
  public Integer getAttuneCost() {
    return stats.attuneCost;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ArtifactStatsDecorator)) {
      return false;
    }
    ArtifactStatsDecorator view = (ArtifactStatsDecorator) obj;
    return view.stats.equals(stats);
  }

  public String toString() {
    return stats.toString();
  }

  @Override
  public int hashCode() {
    return stats.hashCode();
  }

  @Override
  public String getId() {
    return getName().getId();
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return stats.representsItemForUseInCombat();
  }
}
