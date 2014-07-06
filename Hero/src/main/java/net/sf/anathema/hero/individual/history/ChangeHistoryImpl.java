package net.sf.anathema.hero.individual.history;

import net.sf.anathema.hero.individual.change.HeroChange;
import net.sf.anathema.hero.individual.change.TransactionReceiver;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.List;

public class ChangeHistoryImpl implements TransactionReceiver {

  private Hero hero;

  public ChangeHistoryImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public void commitTransaction(HeroChange rootChange, List<HeroChange> followUpChanges) {
    System.err.print("Transaction commited for root change " + rootChange.flavor);
  }
}
