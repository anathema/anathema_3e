package net.sf.anathema.hero.individual.change;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

  private final TransactionReceiver receiver;
  private HeroChange rootChange;
  private List<HeroChange> followUpChanges = new ArrayList<>();

  public Transaction(TransactionReceiver receiver) {
    this.receiver = receiver;
  }

  public void beginAnnounce(HeroChange change) {
    if (rootChange == null) {
      rootChange = change;
    } else {
      followUpChanges.add(change);
    }
  }

  public void finishAnnounce(HeroChange change) {
    if (change == rootChange) {
      receiver.commitTransaction(rootChange, followUpChanges);
      rootChange = null;
      followUpChanges.clear();
    }
  }
}
