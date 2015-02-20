package net.sf.anathema.equipment.editor.model;

import net.sf.anathema.equipment.editor.stats.model.IArtifactStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.ITraitModifyingStatisticsModel;
import net.sf.anathema.equipment.stats.ArmourTag;
import net.sf.anathema.equipment.stats.IEquipmentStats;
import net.sf.anathema.equipment.stats.IWeaponTag;
import net.sf.anathema.equipment.stats.impl.AbstractStats;
import net.sf.anathema.equipment.stats.impl.ArmourStats;
import net.sf.anathema.equipment.stats.impl.ArtifactStats;
import net.sf.anathema.equipment.stats.impl.TraitModifyingStats;
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
      case TraitModifying:
        TraitModifyingStats modifierStats = new TraitModifyingStats();
        ITraitModifyingStatisticsModel modifierModel = model.getTraitModifyingStatisticsModel();
        setName(modifierStats, modifierModel);
        modifierStats.setDDVPoolMod(modifierModel.getDDVModel().getValue());
        modifierStats.setPDVPoolMod(modifierModel.getPDVModel().getValue());
        modifierStats.setMeleeAccuracyMod(modifierModel.getMeleeWeaponAccuracyModel().getValue());
        modifierStats.setMeleeDamageMod(modifierModel.getMeleeWeaponDamageModel().getValue());
        modifierStats.setRangedAccuracyMod(modifierModel.getRangedWeaponAccuracyModel().getValue());
        modifierStats.setRangedDamageMod(modifierModel.getRangedWeaponDamageModel().getValue());
        modifierStats.setJoinBattleMod(modifierModel.getJoinBattleModel().getValue());
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
