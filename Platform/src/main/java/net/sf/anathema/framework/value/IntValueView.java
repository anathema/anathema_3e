package net.sf.anathema.framework.value;

import net.sf.anathema.library.event.IntegerChangedListener;

public interface IntValueView {

  void setValue(int newValue);
  
  void addIntValueChangedListener(IntegerChangedListener listener);

  void removeIntValueChangedListener(IntegerChangedListener listener);
}