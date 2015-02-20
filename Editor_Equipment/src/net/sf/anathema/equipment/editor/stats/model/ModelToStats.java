package net.sf.anathema.equipment.editor.stats.model;

import net.sf.anathema.equipment.stats.ArmourTag;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponTag;
import net.sf.anathema.equipment.stats.impl.AbstractStats;
import net.sf.anathema.equipment.stats.impl.ArmourStats;
import net.sf.anathema.equipment.stats.impl.ArtifactStats;
import net.sf.anathema.equipment.stats.impl.WeaponStats;
import net.sf.anathema.library.identifier.SimpleIdentifier;

public class ModelToStats {

  public IEquipmentStats createStats(IEquipmentStatisticsCreationModel model) {
    switch (model.getEquipmentType()) {
      case Armor:
        ArmourStats armourStats = new ArmourStats();
        IEquipmentStatisticsModel armourModel = model.getArmorModel();
        setName(armourStats, armourModel);
        for (ArmourTag tag : model.getArmorTagsModel().getSelectedTags()) {
          armourStats.addTag(tag);
        }
        return armourStats;
      case Weapon:
        WeaponStats weaponStats = new WeaponStats();
        IEquipmentStatisticsModel weaponModel = model.getWeaponModel();
        setName(weaponStats, weaponModel);
        for (IWeaponTag tag : model.getWeaponTagsModel().getSelectedTags()) {
          weaponStats.addTag(tag);
        }
        return weaponStats;
      case Artifact:
        ArtifactStats artifactStats = new ArtifactStats();
        IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
        setName(artifactStats, artifactModel);
        artifactStats.setAttuneCost(artifactModel.getAttuneCostModel().getValue());
        return artifactStats;
    }
    return null;
  }

  private void setName(AbstractStats stats, IEquipmentStatisticsModel model) {
    String name = model.getName().getText();
    if (name != null) {
      stats.setName(new SimpleIdentifier(name));
    }
  }
}
