package net.sf.anathema.hero.charms.compiler;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.CharmMap;
import net.sf.anathema.hero.environment.initialization.ExtensibleDataSet;

public interface CharmCache extends CharmProvider, CharmMap, ExtensibleDataSet {

  Charm getCharmById(CharmName charmId);
}