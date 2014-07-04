package net.sf.anathema.hero.equipment;

import net.sf.anathema.hero.individual.model.Hero;

public class EquipmentModelFetcher {

  public static EquipmentModel fetch(Hero hero) {
    return hero.getModel(EquipmentModel.ID);
  }
}
