package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.TagsModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.library.event.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.Collection;

public class EquipmentStatisticsCreationModel implements IEquipmentStatisticsCreationModel {

  private final IWeaponTagsModel weaponTagsModel = new WeaponTagsModel();
  private final IEquipmentStatisticsModel weaponNameModel = new WeaponLegalityModel(weaponTagsModel);
  private final ArmourTagsModel armourTagsModel = new ArmourTagsModel();
  private final IEquipmentStatisticsModel armourStatisticsModel = new ArmourStatsticsModel(armourTagsModel);
  private final IArtifactStatisticsModel artifactStatisticsModel = new ArtifactStatisticsModel();
  private final ITraitModifyingStatisticsModel traitModifyingStatisticsModel = new TraitModifyingStatisticsModel();
  private final Announcer<ChangeListener> equipmentTypeChangeControl = Announcer.to(ChangeListener.class);
  private EquipmentStatisticsType statisticsType;
  private Collection<String> existingNames;

  @Override
  public void setEquipmentType(EquipmentStatisticsType statisticsType) {
    if (this.statisticsType == statisticsType) {
      return;
    }
    this.statisticsType = statisticsType;
    equipmentTypeChangeControl.announce().changeOccurred();
  }

  @Override
  public IEquipmentStatisticsModel getWeaponModel() {
    return weaponNameModel;
  }

  @Override
  public TagsModel<ArmourTag> getArmorTagsModel() {
    return armourTagsModel;
  }

  @Override
  public IWeaponTagsModel getWeaponTagsModel() {
    return weaponTagsModel;
  }

  @Override
  public IEquipmentStatisticsModel getArmorModel() {
    return armourStatisticsModel;
  }

  @Override
  public IArtifactStatisticsModel getArtifactStatisticsModel() {
    return artifactStatisticsModel;
  }

  @Override
  public ITraitModifyingStatisticsModel getTraitModifyingStatisticsModel() {
    return traitModifyingStatisticsModel;
  }

  @Override
  public EquipmentStatisticsType getEquipmentType() {
    return statisticsType;
  }

  @Override
  public boolean isNameUnique(String name) {
    return !existingNames.contains(name);
  }

  @Override
  public void setForbiddenNames(Collection<String> definedNames) {
    this.existingNames = definedNames;
  }
}