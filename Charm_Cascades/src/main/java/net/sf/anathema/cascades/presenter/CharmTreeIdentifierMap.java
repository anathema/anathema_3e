package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.display.tree.CharmTreeCollectionMap;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.hero.charms.model.options.CharmTreeCategory;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class CharmTreeIdentifierMap implements CharmTreeCollectionMap {

  Map<Identifier, CharmTreeCategory> charmTreesById = new HashMap<>();

  public void put(Identifier id, CharmTreeCategory tree) {
    charmTreesById.put(id, tree);
  }

  @Override
  public CharmTreeCollection getCharmTree(Identifier type) {
    return charmTreesById.get(type);
  }

  public boolean isEmpty() {
    return charmTreesById.isEmpty();
  }
}