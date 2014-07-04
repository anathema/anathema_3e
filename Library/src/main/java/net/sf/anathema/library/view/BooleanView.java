package net.sf.anathema.library.view;

import net.sf.anathema.library.event.BooleanChangedListener;

public interface BooleanView {

  void setSelected(boolean selected);

  void addChangeListener(BooleanChangedListener listener);
}