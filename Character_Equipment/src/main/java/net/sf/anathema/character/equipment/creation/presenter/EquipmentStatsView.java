package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.hero.display.fx.configurableview.IIntegerSpinner;
import net.sf.anathema.library.interaction.model.ToggleTool;
import net.sf.anathema.library.text.ITextView;
import net.sf.anathema.library.view.BooleanValueView;

public interface EquipmentStatsView {

  ITextView addLineTextView(String nameLabel);

  IIntegerSpinner addIntegerSpinner(String label);

  BooleanValueView addBooleanSelector(String label);

  ToggleTool addToggleTool();
}