package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;

public class CharacterGroupCollection implements CharmTreeCollection {
  private CharmDisplayModel model;

  public CharacterGroupCollection(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public CharmTree[] getAllCharmTrees() {
    return model.getCharmModel().getAllGroups();
  }
}
