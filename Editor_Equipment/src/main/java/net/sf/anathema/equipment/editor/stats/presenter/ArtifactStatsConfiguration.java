package net.sf.anathema.equipment.editor.stats.presenter;

import net.sf.anathema.library.resources.RelativePath;

import static net.sf.anathema.equipment.editor.stats.model.EquipmentStatisticsType.Artifact;

public class ArtifactStatsConfiguration extends NewStatsConfiguration {
  public ArtifactStatsConfiguration() {
    super("Equipment.Creation.Stats.AddArtifactTooltip", new RelativePath("icons/Artifact16.png"),
            "EquipmentStats.Artifact", Artifact);
  }
}