package net.sf.anathema.equipment.editor.stats.model;

import net.sf.anathema.equipment.stats.IWeaponTag;

public interface IWeaponTagsModel extends TagsModel<IWeaponTag> {

  boolean isRangedTypeTagSelected();

  boolean isRangeSelected();

  boolean isDamageTypeSelected();

  boolean isSizeSelected();
}