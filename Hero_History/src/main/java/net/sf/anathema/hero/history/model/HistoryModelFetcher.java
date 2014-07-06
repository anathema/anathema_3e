package net.sf.anathema.hero.history.model;

import net.sf.anathema.hero.individual.model.Hero;

public class HistoryModelFetcher {
  
  public static HistoryModel fetch(Hero hero) {
    return hero.getModel(HistoryModel.ID);
  }
}
