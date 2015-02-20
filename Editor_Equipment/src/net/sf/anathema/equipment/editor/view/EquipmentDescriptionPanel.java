package net.sf.anathema.equipment.editor.view;

import net.sf.anathema.library.text.ITextView;

public interface EquipmentDescriptionPanel {

  ITextView addNameView(String label);

  ITextView addDescriptionView(String label);

  CostSelectionView addCostView(String label);
}