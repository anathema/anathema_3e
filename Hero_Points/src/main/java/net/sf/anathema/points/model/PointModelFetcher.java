package net.sf.anathema.points.model;

import net.sf.anathema.hero.individual.model.Hero;

public class PointModelFetcher {

  public static PointsModel fetch(Hero hero) {
    return hero.getModel(PointsModel.ID);
  }
}
