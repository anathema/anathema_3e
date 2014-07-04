package net.sf.anathema.library.view;

import net.sf.anathema.library.event.IBooleanValueChangedListener;

public interface BooleanValueView {

  void setSelected(boolean selected);

  void addChangeListener(IBooleanValueChangedListener listener);
}