package net.sf.anathema.hero.merits.model;

import net.sf.anathema.hero.individual.model.Hero;

public class MeritsModelFetcher {

  public static MeritsModel fetch(Hero hero) {
    return hero.getModel(MeritsModel.ID);
  }
}
