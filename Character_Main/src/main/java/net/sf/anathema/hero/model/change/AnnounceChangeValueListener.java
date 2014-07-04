package net.sf.anathema.hero.model.change;

import net.sf.anathema.hero.individual.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.change.ChangeFlavor;
import net.sf.anathema.library.event.ObjectChangedListener;

public class AnnounceChangeValueListener implements ObjectChangedListener<String> {
  private final ChangeAnnouncer announcer;
  private final ChangeFlavor flavor;

  public AnnounceChangeValueListener(ChangeAnnouncer announcer) {
    this(announcer, ChangeFlavor.UNSPECIFIED);
  }

  public AnnounceChangeValueListener(ChangeAnnouncer announcer, ChangeFlavor flavor) {
    this.announcer = announcer;
    this.flavor = flavor;
  }

  @Override
  public void valueChanged(String newValue) {
    announcer.announceChangeOf(flavor);
  }
}
