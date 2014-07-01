package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.display.tree.CharmTreeCollectionMap;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategory;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class CharmTreeMap implements CharmTreeCollectionMap {

  Map<CategoryReference, CharmTreeCategory> charmTreesById = new HashMap<>();

  public void put(CategoryReference id, CharmTreeCategory tree) {
    charmTreesById.put(id, tree);
  }

  @Override
  public CharmTreeCollection getCharmTree(CategoryReference type) {
    return charmTreesById.get(type);
  }

  public boolean isEmpty() {
    return charmTreesById.isEmpty();
  }
}