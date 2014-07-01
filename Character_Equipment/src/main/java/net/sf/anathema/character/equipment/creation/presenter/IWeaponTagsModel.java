package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public interface IWeaponTagsModel {

  IWeaponTag[] getAllTags();

  BooleanValueModel getEnabledModel(IWeaponTag tag);

  BooleanValueModel getSelectedModel(IWeaponTag tag);

  IWeaponTag[] getSelectedTags();

  boolean isRangedTypeTagSelected();

  boolean isRangeSelected();

  boolean isDamageTypeSelected();

  boolean isSizeSelected();

  void makeValid();

}