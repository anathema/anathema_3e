package net.sf.anathema.platform.repository;

import net.sf.anathema.library.event.ChangeListener;

public interface ChangeManagement {
  boolean isDirty();

  void setClean();

  @SuppressWarnings("UnusedDeclaration")
  void removeDirtyListener(ChangeListener changeListener);

  void addDirtyListener(ChangeListener changeListener);
}