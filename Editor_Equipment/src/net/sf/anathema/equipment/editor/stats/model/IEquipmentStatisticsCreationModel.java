package net.sf.anathema.equipment.editor.stats.model;

import java.util.Collection;

import net.sf.anathema.equipment.stats.EquipmentStatisticsType;
import net.sf.anathema.equipment.stats.impl.ArmourTag;

public interface IEquipmentStatisticsCreationModel {

  void setEquipmentType(EquipmentStatisticsType type);

  IEquipmentStatisticsModel getArmorModel();
  
  IArtifactStatisticsModel getArtifactStatisticsModel();
  
  ITraitModifyingStatisticsModel getTraitModifyingStatisticsModel();

  IWeaponTagsModel getWeaponTagsModel();

  EquipmentStatisticsType getEquipmentType();

  boolean isNameUnique(String name);

  void setForbiddenNames(Collection<String> definedNames);

  IEquipmentStatisticsModel getWeaponModel();

  TagsModel<ArmourTag> getArmorTagsModel();
}