package net.sf.anathema.equipment.editor.stats.model;

import net.sf.anathema.library.model.BooleanValueModel;

public interface TagsModel<TAG> {
  TAG[] getAllTags();

  BooleanValueModel getEnabledModel(TAG tag);

  BooleanValueModel getSelectedModel(TAG tag);

  void makeValid();

  TAG[] getSelectedTags();
}