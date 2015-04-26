package net.sf.anathema.hero.abilities.advance;

import net.sf.anathema.hero.individual.model.Hero;

public class AbilitiesPointModelFetcher {

  public static AbilitiesPointModel fetch(Hero hero) {
    return hero.getModel(AbilitiesPointModelImpl.ID);
  }

  public static boolean isActive(Hero hero) {
    return fetch(hero) != null;
  }
}
