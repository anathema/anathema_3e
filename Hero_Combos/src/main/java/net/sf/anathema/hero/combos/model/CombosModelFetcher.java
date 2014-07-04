package net.sf.anathema.hero.combos.model;

import net.sf.anathema.hero.combos.display.presenter.CombosModel;
import net.sf.anathema.hero.individual.model.Hero;

public class CombosModelFetcher {

  public static CombosModel fetch(Hero hero) {
    return hero.getModel(CombosModel.ID);
  }
}
