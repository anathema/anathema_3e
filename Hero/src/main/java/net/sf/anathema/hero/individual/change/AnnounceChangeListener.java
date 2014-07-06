package net.sf.anathema.hero.individual.change;

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
    announcer.announceChangeFlavor(flavor);
  }
}
