package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.lib.util.Identifier;

public final class CharacterGroupCharmTree implements CharmTreeCollection {
  private final Identifier cascadeType;
  private CharmDisplayModel model;

  public CharacterGroupCharmTree(CharmDisplayModel model, Identifier cascadeType) {
    this.cascadeType = cascadeType;
    this.model = model;
  }

  @Override
  public boolean isEmpty() {
    return getAllCharmTrees().length > 0;
  }

  @Override
  public CharmTree[] getAllCharmTrees() {
    return model.getCharmModel().getCharmGroups(cascadeType);
  }
}
