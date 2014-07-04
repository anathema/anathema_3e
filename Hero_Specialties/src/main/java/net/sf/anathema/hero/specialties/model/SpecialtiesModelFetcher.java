package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.hero.model.Hero;

public class SpecialtiesModelFetcher {

  public static SpecialtiesModel fetch(Hero hero) {
    return hero.getModel(SpecialtiesModel.ID);
  }
}
