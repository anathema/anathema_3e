package net.sf.anathema.equipment.editor.model;

import net.sf.anathema.equipment.editor.stats.model.IArtifactStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.equipment.editor.stats.model.ITraitModifyingStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.impl.EquipmentStatisticsCreationModel;
import net.sf.anathema.equipment.stats.ArmourTag;
import net.sf.anathema.equipment.stats.EquipmentStatisticsType;
import net.sf.anathema.equipment.stats.IArmourStats;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.ITraitModifyingStats;
import net.sf.anathema.equipment.stats.IWeaponStats;
import net.sf.anathema.equipment.stats.impl.ArtifactStats;
import net.sf.anathema.equipment.stats.impl.WeaponTag;
import net.sf.anathema.library.identifier.Identifier;

public class StatsToModel {

  public IEquipmentStatisticsCreationModel createModel(IEquipmentStats stats) {
    EquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel();
    if (stats instanceof IWeaponStats) {
      IWeaponStats weaponStats = (IWeaponStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.Weapon);
      model.getWeaponModel().getName().setText(weaponStats.getName().getId());
      for (Identifier tag : weaponStats.getTags()) {
        model.getWeaponTagsModel().getSelectedModel((WeaponTag) tag).setValue(true);
      }
      model.getWeaponTagsModel().makeValid();
    } else if (stats instanceof IArmourStats) {
      IArmourStats armourStats = (IArmourStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.Armor);
      model.getArmorModel().getName().setText(armourStats.getName().getId());
      for (Identifier tag : armourStats.getTags()) {
        model.getArmorTagsModel().getSelectedModel((ArmourTag) tag).setValue(true);
      }
      model.getArmorTagsModel().makeValid();
    } else if (stats instanceof ArtifactStats) {
      ArtifactStats artifactStats = (ArtifactStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.Artifact);
      IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
      artifactModel.getName().setText(artifactStats.getName().getId());
      artifactModel.getAttuneCostModel().setValue(artifactStats.getAttuneCost());
    } else if (stats instanceof ITraitModifyingStats)

    {
      ITraitModifyingStats modifierStats = (ITraitModifyingStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.TraitModifying);
      ITraitModifyingStatisticsModel modifierModel = model.getTraitModifyingStatisticsModel();
      modifierModel.getName().setText(modifierStats.getName().getId());
      modifierModel.getDDVModel().setValue(modifierStats.getDDVPoolMod());
      modifierModel.getPDVModel().setValue(modifierStats.getPDVPoolMod());
      modifierModel.getMeleeWeaponAccuracyModel().setValue(modifierStats.getMeleeAccuracyMod());
      modifierModel.getMeleeWeaponDamageModel().setValue(modifierStats.getMeleeDamageMod());
      modifierModel.getRangedWeaponAccuracyModel().setValue(modifierStats.getRangedAccuracyMod());
      modifierModel.getRangedWeaponDamageModel().setValue(modifierStats.getRangedDamageMod());
      modifierModel.getJoinBattleModel().setValue(modifierStats.getJoinBattleMod());
    }

    return model;
  }
}