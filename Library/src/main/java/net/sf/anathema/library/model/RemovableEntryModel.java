package net.sf.anathema.library.model;

import java.util.List;

public interface RemovableEntryModel<E> {

  E commitSelection();

  List<E> getEntries();

  void removeEntry(E entry);

  void addModelChangeListener(RemovableEntryListener<E> listener);
}