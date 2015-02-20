package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.equipment.character.IEquipmentItem;
import net.sf.anathema.equipment.stats.ArtifactAttuneType;
import net.sf.anathema.equipment.stats.IArtifactStats;
import net.sf.anathema.hero.equipment.EquipmentHeroEvaluator;

public class ArtifactPresentationStrategy implements StatsPresentationStrategy {
  private final IArtifactStats stats;
  private final EquipmentHeroEvaluator heroEvaluator;
  private final IEquipmentItem item;

  public ArtifactPresentationStrategy(IArtifactStats stats, EquipmentHeroEvaluator heroEvaluator, IEquipmentItem item) {
    this.stats = stats;
    this.heroEvaluator = heroEvaluator;
    this.item = item;
  }

  @Override
  public boolean shouldStatsBeShown() {
    ArtifactAttuneType[] attuneTypes = heroEvaluator.getAttuneTypes(item);
    for (ArtifactAttuneType type : attuneTypes) {
      if (stats.getAttuneType() == type) {
        return true;
      }
    }
    return false;
  }
}
