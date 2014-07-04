package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagProperties;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.library.model.BooleanValueModel;

public class TagPresenter<TAG> {

  private final TagsModel<TAG> tagsModel;
  private final EquipmentStatsView view;
  private final TagProperties<TAG> tagProperties;

  public TagPresenter(TagsModel<TAG> tagsModel, EquipmentStatsView view, TagProperties<TAG> tagProperties) {
    this.tagsModel = tagsModel;
    this.view = view;
    this.tagProperties = tagProperties;
  }

  public void initPresentation() {
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
    for (TAG tag : tagsModel.getAllTags()) {
      ToggleTool tool = view.addToggleTool();
      tool.setText(tagProperties.getLabel(tag));
      tool.setTooltip(tagProperties.getToolTip(tag));
      booleanValuePresentation.initPresentation(tool, tagsModel.getSelectedModel(tag));
      BooleanValueModel enabledModel = tagsModel.getEnabledModel(tag);
      enabledModel.addChangeListener(newValue -> this.enableBasedOnModelState(enabledModel, tool));
      enableBasedOnModelState(enabledModel, tool);
    }
  }

  private void enableBasedOnModelState(BooleanValueModel enabledModel, ToggleTool tool) {
    if (enabledModel.getValue()) {
      tool.enable();
    } else {
      tool.disable();
    }
  }
}