package net.sf.anathema.library.view;

import net.sf.anathema.library.event.BooleanChangedListener;

public interface BooleanValueView {

  void setSelected(boolean selected);

  void addChangeListener(BooleanChangedListener listener);
}