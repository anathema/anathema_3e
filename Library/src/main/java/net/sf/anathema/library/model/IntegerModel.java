package net.sf.anathema.library.model;

import net.sf.anathema.library.event.ChangeListener;

public interface IntegerModel {

  int getValue();

  void setValue(int value);

  void addChangeListener(ChangeListener listener);
}