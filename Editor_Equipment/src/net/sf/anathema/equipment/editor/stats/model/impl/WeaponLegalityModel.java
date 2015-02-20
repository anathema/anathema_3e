package net.sf.anathema.equipment.editor.stats.model.impl;

import net.sf.anathema.equipment.editor.stats.model.IEquipmentStatisticsModel;
import net.sf.anathema.equipment.editor.stats.model.IWeaponTagsModel;
import net.sf.anathema.equipment.stats.impl.IWeaponTag;

public class WeaponLegalityModel extends EquipmentStatisticsModel implements IEquipmentStatisticsModel {

  private IWeaponTagsModel weaponTagsModel;

  public WeaponLegalityModel(IWeaponTagsModel weaponTagsModel) {
    this.weaponTagsModel = weaponTagsModel;
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      weaponTagsModel.getSelectedModel(tag).addChangeListener(selected -> announceValidationChange());
    }
  }

  @SuppressWarnings("SimplifiableIfStatement")
  @Override
  public boolean isValid() {
    if (!super.isValid()) {
      return false;
    }
    if (!weaponTagsModel.isSizeSelected()) {
      return false;
    }
    if (!weaponTagsModel.isDamageTypeSelected()) {
      return false;
    }
    if (!weaponTagsModel.isRangedTypeTagSelected()) {
      return true;
    }
    return weaponTagsModel.isRangeSelected();
  }
}