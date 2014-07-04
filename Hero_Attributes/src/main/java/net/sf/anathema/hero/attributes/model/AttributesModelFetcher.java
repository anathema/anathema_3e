package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.individual.model.Hero;

public class AttributesModelFetcher {

  public static AttributeModel fetch(Hero hero) {
    return hero.getModel(AttributeModel.ID);
  }
}
