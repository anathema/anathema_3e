package net.sf.anathema.hero.model.change;

import net.sf.anathema.hero.individual.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.individual.model.change.ChangeFlavor;
import net.sf.anathema.hero.individual.model.change.FlavoredChangeListener;
import org.jmock.example.announcer.Announcer;

public class ChangeAnnouncerImpl implements ChangeAnnouncer {
  private final Announcer<FlavoredChangeListener> announcer = Announcer.to(FlavoredChangeListener.class);

  @Override
  public void addListener(FlavoredChangeListener listener) {
    announcer.addListener(listener);
  }

  @Override
  public void announceChangeOf(ChangeFlavor flavor) {
    announcer.announce().changeOccurred(flavor);
  }
}
