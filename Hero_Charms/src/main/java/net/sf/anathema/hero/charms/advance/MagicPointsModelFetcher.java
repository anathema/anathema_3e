package net.sf.anathema.hero.charms.advance;

import net.sf.anathema.hero.individual.model.Hero;

public class MagicPointsModelFetcher {

  public static MagicPointsModel fetch(Hero hero) {
    return hero.getModel(MagicPointsModel.ID);
  }
}
