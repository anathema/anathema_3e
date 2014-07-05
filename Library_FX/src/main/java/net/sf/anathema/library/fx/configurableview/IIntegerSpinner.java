package net.sf.anathema.library.fx.configurableview;

import net.sf.anathema.library.event.IntegerChangedListener;

public interface IIntegerSpinner {
  void setValue(int newValue);

  void setMinimum(Integer minimum);
  
  void setMaximum(Integer maximum);

  void addChangeListener(IntegerChangedListener listener);

  void setEnabled(boolean enabled);
}
