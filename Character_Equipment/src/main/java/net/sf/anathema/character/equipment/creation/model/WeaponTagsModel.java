package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Lethal;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Light;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getDamageTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getRangeTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getRangedWeaponExclusiveTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getRangedWeaponTypeTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getSizeTags;

public class WeaponTagsModel implements IWeaponTagsModel {

  private final Map<IWeaponTag, BooleanValueModel> enabledMap = new HashMap<>();
  private final Map<IWeaponTag, BooleanValueModel> selectedMap = new HashMap<>();

  public WeaponTagsModel() {
    for (WeaponTag tag : WeaponTag.values()) {
      selectedMap.put(tag, new BooleanValueModel(false));
      enabledMap.put(tag, new BooleanValueModel(true));
    }
    enableRangedCombatTags();
    updateOnChange(getSizeTags());
    updateOnChange(getDamageTags());
    updateOnChange(getRangeTags());
    for (WeaponTag typeTag : getRangedWeaponTypeTags()) {
      getSelectedModel(typeTag).addChangeListener(newValue -> enableRangedCombatTags());
    }
  }

  @Override
  public IWeaponTag[] getAllTags() {
    return WeaponTag.values();
  }

  @Override
  public BooleanValueModel getEnabledModel(IWeaponTag tag) {
    return enabledMap.get(tag);
  }

  @Override
  public BooleanValueModel getSelectedModel(IWeaponTag tag) {
    return selectedMap.get(tag);
  }

  @Override
  public IWeaponTag[] getSelectedTags() {
    List<IWeaponTag> tags = new ArrayList<>();
    for (IWeaponTag tag : selectedMap.keySet()) {
      if (isSelected(tag)) {
        tags.add(tag);
      }
    }
    return tags.toArray(new IWeaponTag[tags.size()]);
  }

  private boolean isSelected(IWeaponTag tag) {
    return getSelectedModel(tag).getValue();
  }

  private void setEnabled(boolean enabled, WeaponTag tag) {
    getEnabledModel(tag).setValue(enabled);
    if (!enabled) {
      getSelectedModel(tag).setValue(false);
    }
  }

  private void updateOnChange(WeaponTag[] tags) {
    for (WeaponTag sizeTag : tags) {
      getSelectedModel(sizeTag).addChangeListener(newValue -> allowOneOrAll(tags));
    }
  }

  private void enableRangedCombatTags() {
    if (isRangedTypeTagSelected()) {
      allowOnlyOne(getRangedWeaponTypeTags());
      allowAll(getRangedWeaponExclusiveTags());
    } else {
      allowAll(getRangedWeaponTypeTags());
      allowNone(getRangedWeaponExclusiveTags());
    }
  }

  @Override
  public boolean isSizeSelected() {
    return isAnySelected(getSizeTags());
  }

  @Override
  public void makeValid() {
    if (!isSizeSelected()) {
      getSelectedModel(Light).setValue(true);
    }
    if (!isDamageTypeSelected()) {
      getSelectedModel(Lethal).setValue(true);
    }
  }

  @Override
  public boolean isDamageTypeSelected() {
    return isAnySelected(getDamageTags());
  }

  @Override
  public boolean isRangedTypeTagSelected() {
    return isAnySelected(getRangedWeaponTypeTags());
  }

  @Override
  public boolean isRangeSelected() {
    return isAnySelected(getRangeTags());
  }

  private boolean isAnySelected(WeaponTag[] tags) {
    for (WeaponTag tag : tags) {
      if (isSelected(tag)) {
        return true;
      }
    }
    return false;
  }

  private void allowOneOrAll(WeaponTag[] tags) {
    if (isAnySelected(tags)) {
      allowOnlyOne(tags);
    } else {
      allowAll(tags);
    }
  }

  private void allowNone(WeaponTag[] tags) {
    for (WeaponTag tag : tags) {
      setEnabled(false, tag);
    }
  }

  private void allowAll(WeaponTag[] tags) {
    for (WeaponTag tag : tags) {
      setEnabled(true, tag);
    }
  }

  private void allowOnlyOne(WeaponTag[] tags) {
    for (WeaponTag tag : tags) {
      setEnabled(isSelected(tag), tag);
    }
  }
}