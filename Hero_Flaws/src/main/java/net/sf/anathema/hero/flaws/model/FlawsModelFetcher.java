package net.sf.anathema.hero.flaws.model;

import net.sf.anathema.hero.individual.model.Hero;

public class FlawsModelFetcher {

  public static FlawsModel fetch(Hero hero) {
    return hero.getModel(FlawsModel.ID);
  }
}
