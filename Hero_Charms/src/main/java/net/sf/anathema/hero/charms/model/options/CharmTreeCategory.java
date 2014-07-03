package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.charms.model.CharmTreeCollection;

public interface CharmTreeCategory extends CharmMap, CharmTreeCollection {

  CategoryReference getReference();
}