package net.sf.anathema.library.model;

import net.sf.anathema.library.event.ChangeListener;

public interface IChangeableModel {

  void addChangeListener(ChangeListener listener);

  void removeChangeListener(ChangeListener listener);
}