package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.library.model.BooleanValueModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicTagsModel<TAG> {

  private final Map<TAG, BooleanValueModel> enabledMap = new HashMap<>();
  private final Map<TAG, BooleanValueModel> selectedMap = new HashMap<>();
  private final TAG[] tags;

  public BasicTagsModel(TAG[] tags) {
    this.tags = tags;
    for (TAG tag : tags) {
      selectedMap.put(tag, new BooleanValueModel(false));
      enabledMap.put(tag, new BooleanValueModel(true));
    }
  }

  public BooleanValueModel getEnablement(TAG tag) {
    return enabledMap.get(tag);
  }

  public BooleanValueModel getSelection(TAG tag) {
    return selectedMap.get(tag);
  }

  public TAG[] getAllTags() {
    return tags;
  }

  public List<TAG> getSelectedTags() {
    List<TAG> tags = new ArrayList<>();
    for (TAG tag : selectedMap.keySet()) {
      if (isSelected(tag)) {
        tags.add(tag);
      }
    }
    return tags;
  }

  public void updateOnChange(TAG[] tags) {
    for (TAG tag : tags) {
      getSelection(tag).addChangeListener(newValue -> allowOneOrAll(tags));
    }
  }

  public boolean isSelected(TAG tag) {
    return getSelection(tag).getValue();
  }

  public boolean isAnySelected(TAG[] tags) {
    for (TAG tag : tags) {
      if (isSelected(tag)) {
        return true;
      }
    }
    return false;
  }

  public void allowOneOrAll(TAG[] tags) {
    if (isAnySelected(tags)) {
      allowOnlyOne(tags);
    } else {
      allowAll(tags);
    }
  }

  public void allowNone(TAG[] tags) {
    for (TAG tag : tags) {
      setEnabled(false, tag);
    }
  }

  public void allowAll(TAG[] tags) {
    for (TAG tag : tags) {
      setEnabled(true, tag);
    }
  }

  public void allowOnlyOne(TAG[] tags) {
    for (TAG tag : tags) {
      setEnabled(isSelected(tag), tag);
    }
  }

  private void setEnabled(boolean enabled, TAG tag) {
    getEnablement(tag).setValue(enabled);
    if (!enabled) {
      getSelection(tag).setValue(false);
    }
  }
}