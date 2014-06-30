package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.model.CharmTreeCollection;
import net.sf.anathema.lib.util.Identifier;

public interface CharmTreeCollectionMap {

  CharmTreeCollection getCharmTree(Identifier type);
}