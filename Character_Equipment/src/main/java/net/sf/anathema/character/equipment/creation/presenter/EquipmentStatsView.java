package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.library.fx.configurableview.IIntegerSpinner;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.BooleanView;

public interface EquipmentStatsView {

  ITextView addLineTextView(String nameLabel);

  IIntegerSpinner addIntegerSpinner(String label);

  BooleanView addBooleanSelector(String label);

  ToggleTool addToggleTool();
}