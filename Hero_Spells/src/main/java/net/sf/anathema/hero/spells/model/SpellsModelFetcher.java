package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.individual.model.Hero;

public class SpellsModelFetcher {

  public static SpellsModel fetch(Hero hero) {
    return hero.getModel(SpellsModel.ID);
  }
}
