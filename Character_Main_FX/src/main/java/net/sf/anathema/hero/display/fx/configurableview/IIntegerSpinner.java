package net.sf.anathema.hero.display.fx.configurableview;

import net.sf.anathema.library.event.IntValueChangedListener;

public interface IIntegerSpinner {
  void setValue(int newValue);

  void setMinimum(Integer minimum);
  
  void setMaximum(Integer maximum);

  void addChangeListener(IntValueChangedListener listener);

  void setEnabled(boolean enabled);
}
