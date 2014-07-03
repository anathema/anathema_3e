package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CharmName;

public interface CharmMap {

  Charm getCharmById(CharmName charmId);
}