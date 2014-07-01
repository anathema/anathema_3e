package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public class BasicWeaponPresenter {

  private IWeaponTagsModel weaponTagsModel;
  private final EquipmentStatsView view;
  private final TagPageProperties tagProperties;

  public BasicWeaponPresenter(IWeaponTagsModel weaponTagsModel,
                              EquipmentStatsView view,
                              TagPageProperties tagProperties) {
    this.weaponTagsModel = weaponTagsModel;
    this.view = view;
    this.tagProperties = tagProperties;
  }

  public void initPresentation() {
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      ToggleTool tool = view.addToggleTool();
      tool.setText(tagProperties.getLabel(tag));
      tool.setTooltip(tagProperties.getToolTip(tag));
      booleanValuePresentation.initPresentation(tool, weaponTagsModel.getSelectedModel(tag));
      BooleanValueModel enabledModel = weaponTagsModel.getEnabledModel(tag);
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