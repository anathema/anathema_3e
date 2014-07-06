package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.TagsModel;
import net.sf.anathema.library.model.BooleanValueModel;

import java.util.List;

import static net.sf.anathema.character.equipment.creation.model.ArmourTag.Light;
import static net.sf.anathema.character.equipment.creation.model.ArmourTag.getSizeTags;

public class ArmourTagsModel implements TagsModel<ArmourTag> {

  private final BasicTagsModel<ArmourTag> model = new BasicTagsModel<>(ArmourTag.values());

  public ArmourTagsModel() {
    model.updateOnChange(getSizeTags());
  }

  @Override
  public ArmourTag[] getAllTags() {
    return model.getAllTags();
  }

  @Override
  public BooleanValueModel getEnabledModel(ArmourTag armourTag) {
    return model.getEnablement(armourTag);
  }

  @Override
  public BooleanValueModel getSelectedModel(ArmourTag armourTag) {
    return model.getSelection(armourTag);
  }

  @Override
  public void makeValid() {
    if (!isSizeSelected()){
        model.getSelection(Light).setValue(true);
    }
  }

  @Override
  public ArmourTag[] getSelectedTags() {
    List<ArmourTag> selectedTags = model.getSelectedTags();
    return selectedTags.toArray(new ArmourTag[selectedTags.size()]);
  }

  public boolean isSizeSelected() {
    return model.isAnySelected(getSizeTags());
  }
}