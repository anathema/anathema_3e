package net.sf.anathema.library.view;

import net.sf.anathema.library.event.IntegerChangedListener;

public interface IntegerView {

  void addChangeListener(IntegerChangedListener intValueChangedListener);
}