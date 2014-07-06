package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;

import java.util.Collection;

public class CharacterGroupCollection implements CharmTreeCollection {
  private CharmDisplayModel model;

  public CharacterGroupCollection(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public boolean isEmpty() {
    return getAllCharmTrees().isEmpty();
  }

  @Override
  public Collection<CharmTree> getAllCharmTrees() {
    return model.getCharmModel().getAllTrees();
  }
}
