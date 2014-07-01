package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.TreeCategoryReference;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;

public interface CharmTreeCategory extends CharmIdMap, CharmTreeCollection {

  TreeCategoryReference getReference();
}