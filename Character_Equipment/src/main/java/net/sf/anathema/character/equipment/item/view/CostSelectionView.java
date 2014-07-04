package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.library.event.SelectionIntValueChangedListener;

public interface CostSelectionView {
  void setValue(ItemCost cost);

  void addSelectionChangedListener(SelectionIntValueChangedListener<String> listener);

  void setSelectableBackgrounds(String[] backgrounds);
}
