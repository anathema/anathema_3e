package net.sf.anathema.hero.framework;

import net.sf.anathema.library.event.ChangeListener;

public interface IIntegerDescription {

  int getValue();

  void setValue(int value);

  void addChangeListener(ChangeListener listener);
}