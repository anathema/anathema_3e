package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;

import java.util.Collection;

public final class CharacterGroupCharmTree implements CharmTreeCollection {
  private final CategoryReference cascadeType;
  private CharmDisplayModel model;

  public CharacterGroupCharmTree(CharmDisplayModel model, CategoryReference cascadeType) {
    this.cascadeType = cascadeType;
    this.model = model;
  }

  @Override
  public boolean isEmpty() {
    return getAllCharmTrees().isEmpty();
  }

  @Override
  public Collection<CharmTree> getAllCharmTrees() {
    return model.getCharmModel().getTreesFor(cascadeType);
  }
}
