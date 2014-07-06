package net.sf.anathema.hero.individual.history;

import net.sf.anathema.hero.individual.change.HeroChange;
import net.sf.anathema.hero.individual.change.TransactionReceiver;
import net.sf.anathema.hero.individual.model.Hero;

import java.util.List;

import static java.text.MessageFormat.format;

public class ChangeHistoryImpl implements TransactionReceiver {

  private Hero hero;

  public ChangeHistoryImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public void commitTransaction(HeroChange rootChange, List<HeroChange> followUpChanges) {
    String pattern = "Transaction commited for root change {0} with {1} follow ups";
    System.err.println(format(pattern, rootChange.flavor, followUpChanges.size()));
  }
}
