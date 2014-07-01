package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public interface TagsModel<TAG> {
  TAG[] getAllTags();

  BooleanValueModel getEnabledModel(TAG tag);

  BooleanValueModel getSelectedModel(TAG tag);

  void makeValid();

  TAG[] getSelectedTags();
}