package net.sf.anathema.hero.thaumaturgy.model;

import net.sf.anathema.hero.individual.model.Hero;

public class ThaumaturgyModelFetcher {
	public static ThaumaturgyModel fetch(Hero hero) {
    return hero.getModel(ThaumaturgyModel.ID);
  }
}
