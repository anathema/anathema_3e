package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.hero.individual.model.Hero;

public class StatsModelFetcher {

  public static StatsModel fetch(Hero hero) {
    return hero.getModel(StatsModel.ID);
  }
}
