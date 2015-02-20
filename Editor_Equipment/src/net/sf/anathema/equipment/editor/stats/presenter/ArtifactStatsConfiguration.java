package net.sf.anathema.equipment.editor.stats.presenter;

import static net.sf.anathema.equipment.stats.EquipmentStatisticsType.Artifact;
import net.sf.anathema.library.resources.RelativePath;

public class ArtifactStatsConfiguration extends NewStatsConfiguration {
  public ArtifactStatsConfiguration() {
    super("Equipment.Creation.Stats.AddArtifactTooltip", new RelativePath("icons/Artifact16.png"),
            "EquipmentStats.Artifact", Artifact);
  }
}