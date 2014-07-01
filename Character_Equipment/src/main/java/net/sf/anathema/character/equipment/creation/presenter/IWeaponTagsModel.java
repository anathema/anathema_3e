package net.sf.anathema.character.equipment.creation.presenter;

public interface IWeaponTagsModel extends TagsModel<IWeaponTag> {

  boolean isRangedTypeTagSelected();

  boolean isRangeSelected();

  boolean isDamageTypeSelected();

  boolean isSizeSelected();
}