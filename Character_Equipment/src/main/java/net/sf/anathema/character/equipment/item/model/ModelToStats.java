package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.character.model.stats.AbstractStats;
import net.sf.anathema.character.equipment.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.character.model.stats.ArtifactStats;
import net.sf.anathema.character.equipment.character.model.stats.TraitModifyingStats;
import net.sf.anathema.character.equipment.character.model.stats.WeaponStats;
import net.sf.anathema.character.equipment.creation.presenter.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.health.model.HealthType;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class ModelToStats {

  public IEquipmentStats createStats(IEquipmentStatisticsCreationModel model) {
    switch (model.getEquipmentType()) {
      case Armor:
        ArmourStats armourStats = new ArmourStats();
        IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
        setName(armourStats, armourModel);
        armourStats.setFatigue(armourModel.getFatigueModel().getValue());
        armourStats.setMobilityPenalty(armourModel.getMobilityPenaltyModel().getValue());
        for (HealthType healthType : HealthType.values()) {
          armourStats.setSoak(healthType, armourModel.getSoakModel(healthType).getValue());
          armourStats.setHardness(healthType, armourModel.getHardnessModel(healthType).getValue());
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
        artifactStats.setAllowForeignAttunement(artifactModel.getForeignAttunementModel().getValue());
        artifactStats.setRequireAttunement(artifactModel.getRequireAttunementModel().getValue());
        return artifactStats;
      case TraitModifying:
        TraitModifyingStats modifierStats = new TraitModifyingStats();
        ITraitModifyingStatisticsModel modifierModel = model.getTraitModifyingStatisticsModel();
        setName(modifierStats, modifierModel);
        modifierStats.setDDVPoolMod(modifierModel.getDDVModel().getValue());
        modifierStats.setPDVPoolMod(modifierModel.getPDVModel().getValue());
        modifierStats.setMDDVPoolMod(modifierModel.getMDDVModel().getValue());
        modifierStats.setMPDVPoolMod(modifierModel.getMPDVModel().getValue());
        modifierStats.setMeleeSpeedMod(modifierModel.getMeleeWeaponSpeedModel().getValue());
        modifierStats.setMeleeAccuracyMod(modifierModel.getMeleeWeaponAccuracyModel().getValue());
        modifierStats.setMeleeDamageMod(modifierModel.getMeleeWeaponDamageModel().getValue());
        modifierStats.setMeleeRateMod(modifierModel.getMeleeWeaponRateModel().getValue());
        modifierStats.setRangedSpeedMod(modifierModel.getRangedWeaponSpeedModel().getValue());
        modifierStats.setRangedAccuracyMod(modifierModel.getRangedWeaponAccuracyModel().getValue());
        modifierStats.setRangedDamageMod(modifierModel.getRangedWeaponDamageModel().getValue());
        modifierStats.setRangedRateMod(modifierModel.getRangedWeaponRateModel().getValue());
        modifierStats.setJoinBattleMod(modifierModel.getJoinBattleModel().getValue());
        modifierStats.setJoinDebateMod(modifierModel.getJoinDebateModel().getValue());
        modifierStats.setJoinWarMod(modifierModel.getJoinWarModel().getValue());
        return modifierStats;
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
