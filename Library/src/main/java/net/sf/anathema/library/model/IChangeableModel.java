package net.sf.anathema.library.model;

import net.sf.anathema.library.event.ChangeListener;

public interface IChangeableModel {

  void addChangeListener(ChangeListener listener);

  @SuppressWarnings("UnusedDeclaration")
  void removeChangeListener(ChangeListener listener);
}