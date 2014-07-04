package net.sf.anathema.platform.tree.display;

import net.sf.anathema.library.view.IntValueView;

public interface CategorizedSpecialNodeView extends SpecialNodeView{
  IntValueView addCategory(String labelText, int maxValue, int value);
}