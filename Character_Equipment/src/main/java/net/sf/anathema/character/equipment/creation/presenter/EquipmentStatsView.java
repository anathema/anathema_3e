package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.hero.display.fx.configurableview.IIntegerSpinner;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.library.view.BooleanValueView;

public interface EquipmentStatsView {

  ITextView addLineTextView(String nameLabel);

  IIntegerSpinner addIntegerSpinner(String label);

  BooleanValueView addBooleanSelector(String label);

  ToggleTool addToggleTool();
}