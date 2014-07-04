package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.model.ArmourTag;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;

import java.util.Collection;

public interface IEquipmentStatisticsCreationModel {

  void setEquipmentType(EquipmentStatisticsType type);

  IArmourStatisticsModel getArmourStatisticsModel();
  
  IArtifactStatisticsModel getArtifactStatisticsModel();
  
  ITraitModifyingStatisticsModel getTraitModifyingStatisticsModel();

  IWeaponTagsModel getWeaponTagsModel();

  EquipmentStatisticsType getEquipmentType();

  boolean isNameUnique(String name);

  void setForbiddenNames(Collection<String> definedNames);

  IEquipmentStatisticsModel getWeaponModel();

  TagsModel<ArmourTag> getArmorTagsModel();
}