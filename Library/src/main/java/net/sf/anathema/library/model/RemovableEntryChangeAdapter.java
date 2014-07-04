package net.sf.anathema.library.model;

import net.sf.anathema.library.change.ChangeAnnouncer;
import net.sf.anathema.library.change.ChangeFlavor;

public class RemovableEntryChangeAdapter<E> implements RemovableEntryListener<E> {
  private final ChangeAnnouncer announcer;

  public RemovableEntryChangeAdapter(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void entryAdded(E entry) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void entryRemoved(E entry) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void entryAllowed(boolean complete) {
    // nothing to do
  }
}
