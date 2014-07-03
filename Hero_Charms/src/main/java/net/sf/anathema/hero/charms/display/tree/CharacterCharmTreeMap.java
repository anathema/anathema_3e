package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;

public class CharacterCharmTreeMap implements CharmTreeCollectionMap {
  private CharmDisplayModel charmModel;

  public CharacterCharmTreeMap(CharmDisplayModel charmModel) {
    this.charmModel = charmModel;
  }

  @Override
  public CharmTreeCollection getCharmTree(CategoryReference type) {
    return new CharacterGroupCharmTree(charmModel, type);
  }
}