package net.sf.anathema.equipment.editor.stats.model;

import net.sf.anathema.equipment.stats.ArmourTag;

import java.util.Collection;

public interface IEquipmentStatisticsCreationModel {

  void setEquipmentType(EquipmentStatisticsType type);

  IEquipmentStatisticsModel getArmorModel();
  
  IArtifactStatisticsModel getArtifactStatisticsModel();

  IWeaponTagsModel getWeaponTagsModel();

  EquipmentStatisticsType getEquipmentType();

  boolean isNameUnique(String name);

  void setForbiddenNames(Collection<String> definedNames);

  IEquipmentStatisticsModel getWeaponModel();

  TagsModel<ArmourTag> getArmorTagsModel();
}