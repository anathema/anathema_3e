package net.sf.anathema.hero.health.model;

import net.sf.anathema.hero.individual.model.Hero;

public class HealthModelFetcher {

  public static HealthModel fetch(Hero hero) {
    return hero.getModel(HealthModel.ID);
  }
}
