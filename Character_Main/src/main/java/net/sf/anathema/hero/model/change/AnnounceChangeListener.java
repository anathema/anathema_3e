package net.sf.anathema.hero.model.change;

import net.sf.anathema.hero.individual.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.change.ChangeFlavor;
import net.sf.anathema.library.event.ChangeListener;

public class AnnounceChangeListener implements ChangeListener {
  private final ChangeAnnouncer announcer;
  private final ChangeFlavor flavor;

  public AnnounceChangeListener(ChangeAnnouncer announcer, ChangeFlavor flavor) {
    this.announcer = announcer;
    this.flavor = flavor;
  }

  @Override
  public void changeOccurred() {
    announcer.announceChangeOf(flavor);
  }
}
