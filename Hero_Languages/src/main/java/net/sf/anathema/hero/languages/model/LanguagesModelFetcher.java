package net.sf.anathema.hero.languages.model;

import net.sf.anathema.hero.individual.model.Hero;

public class LanguagesModelFetcher {

  public static LanguagesModel fetch(Hero hero) {
    return hero.getModel(LanguagesModel.ID);
  }
}
