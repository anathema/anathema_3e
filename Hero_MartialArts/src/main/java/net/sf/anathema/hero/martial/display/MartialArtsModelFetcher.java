package net.sf.anathema.hero.martial.display;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.martial.model.MartialArtsModel;

public class MartialArtsModelFetcher {
  public static MartialArtsModel fetch(Hero hero) {
    return hero.getModel(MartialArtsModel.ID);
  }
}
