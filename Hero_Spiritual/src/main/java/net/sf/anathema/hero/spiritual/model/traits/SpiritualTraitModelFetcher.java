package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.individual.model.Hero;

public class SpiritualTraitModelFetcher {

  public static SpiritualTraitModel fetch(Hero hero) {
    return hero.getModel(SpiritualTraitModel.ID);
  }
}
