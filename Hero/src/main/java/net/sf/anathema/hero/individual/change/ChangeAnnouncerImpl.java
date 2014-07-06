package net.sf.anathema.hero.individual.change;

import org.jmock.example.announcer.Announcer;

public class ChangeAnnouncerImpl implements ChangeAnnouncer {

  private final Announcer<FlavoredChangeListener> flavorAnnouncer = new Announcer<>(FlavoredChangeListener.class);
  private final Transaction transaction;

  public ChangeAnnouncerImpl(TransactionReceiver receiver) {
    this.transaction = new Transaction(receiver);
  }

  @Override
  public void addListener(FlavoredChangeListener listener) {
    flavorAnnouncer.addListener(listener);
  }

  @Override
  public synchronized void announceChangeFlavor(ChangeFlavor flavor) {
    flavorAnnouncer.announce().changeOccurred(flavor);
  }

  @Override
  public void announceChange(HeroChange change) {
    transaction.beginAnnounce(change);
    try {
      flavorAnnouncer.announce().changeOccurred(change.flavor);
    } finally {
      transaction.finishAnnounce(change);
    }
  }
}
