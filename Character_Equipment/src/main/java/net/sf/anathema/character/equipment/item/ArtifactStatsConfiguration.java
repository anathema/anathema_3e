package net.sf.anathema.character.equipment.item;

import net.sf.anathema.library.resources.RelativePath;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;

public class ArtifactStatsConfiguration extends NewStatsConfiguration {
  public ArtifactStatsConfiguration() {
    super("Equipment.Creation.Stats.AddArtifactTooltip", new RelativePath("icons/Artifact16.png"),
            "EquipmentStats.Artifact", Artifact);
  }
}