package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;

public interface CharmTreeCollectionMap {

  CharmTreeCollection getCharmTree(CategoryReference type);
}