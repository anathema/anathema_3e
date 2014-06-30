package net.sf.anathema.hero.framework.library.removableentry;

public interface RemovableEntryListener<E> {

  void entryAdded(E entry);

  void entryRemoved(E entry);

  void entryAllowed(boolean complete);
}