package net.sf.anathema.hero.individual.change;

import java.util.List;

public interface TransactionReceiver {

  void commitTransaction(HeroChange rootChange, List<HeroChange> followUpChanges);
}
