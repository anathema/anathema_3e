package net.sf.anathema.hero.concept;

import net.sf.anathema.hero.individual.model.Hero;

public class HeroConceptFetcher {

  public static HeroConcept fetch(Hero hero) {
    return hero.getModel(HeroConcept.ID);
  }
}
