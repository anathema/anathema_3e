package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;

public interface CharmIdMap {

  Charm getCharmById(CharmName charmId);
}