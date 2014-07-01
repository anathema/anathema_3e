package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

import java.util.List;

import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Lethal;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.Light;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getDamageTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getRangeTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getRangedWeaponExclusiveTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getRangedWeaponTypeTags;
import static net.sf.anathema.character.equipment.creation.model.WeaponTag.getSizeTags;

public class WeaponTagsModel implements IWeaponTagsModel {

  private final BasicTagsModel<IWeaponTag> model = new BasicTagsModel<>(WeaponTag.values());

  public WeaponTagsModel() {
    model.updateOnChange(getSizeTags());
    model.updateOnChange(getDamageTags());
    model.updateOnChange(getRangeTags());
    for (WeaponTag typeTag : getRangedWeaponTypeTags()) {
      getSelectedModel(typeTag).addChangeListener(newValue -> enableRangedCombatTags());
    }
    enableRangedCombatTags();
  }

  @Override
  public IWeaponTag[] getAllTags() {
    return model.getAllTags();
  }

  @Override
  public BooleanValueModel getEnabledModel(IWeaponTag tag) {
    return model.getEnablement(tag);
  }

  @Override
  public BooleanValueModel getSelectedModel(IWeaponTag tag) {
    return model.getSelection(tag);
  }

  @Override
  public IWeaponTag[] getSelectedTags() {
    List<IWeaponTag> selectedTags = model.getSelectedTags();
    return selectedTags.toArray(new IWeaponTag[selectedTags.size()]);
  }

  private void enableRangedCombatTags() {
    if (isRangedTypeTagSelected()) {
      model.allowOnlyOne(getRangedWeaponTypeTags());
      model.allowAll(getRangedWeaponExclusiveTags());
    } else {
      model.allowAll(getRangedWeaponTypeTags());
      model.allowNone(getRangedWeaponExclusiveTags());
    }
  }

  @Override
  public boolean isSizeSelected() {
    return model.isAnySelected(getSizeTags());
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
    return model.isAnySelected(getDamageTags());
  }

  @Override
  public boolean isRangedTypeTagSelected() {
    return model.isAnySelected(getRangedWeaponTypeTags());
  }

  @Override
  public boolean isRangeSelected() {
    return model.isAnySelected(getRangeTags());
  }
}