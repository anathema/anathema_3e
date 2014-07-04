package net.sf.anathema.hero.elsewhere.experience;

import net.sf.anathema.hero.individual.model.Hero;

public class ExperienceModelFetcher {

  public static ExperienceModel fetch(Hero hero) {
    return hero.getModel(ExperienceModel.ID);
  }
}
