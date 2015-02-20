package net.sf.anathema.equipment.editor.stats.model.impl;

import java.util.List;

import net.sf.anathema.equipment.editor.stats.model.TagsModel;
import net.sf.anathema.equipment.stats.impl.ArmourTag;
import net.sf.anathema.equipment.stats.impl.BasicTagsModel;
import net.sf.anathema.library.model.BooleanValueModel;

import static net.sf.anathema.equipment.stats.impl.ArmourTag.getSizeTags;
import static net.sf.anathema.equipment.stats.impl.ArmourTag.Light;

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