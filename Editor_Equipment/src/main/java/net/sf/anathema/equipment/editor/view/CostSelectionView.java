package net.sf.anathema.equipment.editor.view;

import java.util.Collection;

import net.sf.anathema.equipment.template.ItemCost;
import net.sf.anathema.library.event.SelectionIntValueChangedListener;

public interface CostSelectionView {
  void setValue(ItemCost cost);

  void addSelectionChangedListener(SelectionIntValueChangedListener<String> listener);

  void setSelectableBackgrounds(Collection<String> backgrounds);
}
