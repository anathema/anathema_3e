package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.individual.model.Hero;

public class GreatCurseFetcher {

  public static LimitBreakModel fetch(Hero hero) {
    return hero.getModel(LimitBreakModel.ID);
  }
}
