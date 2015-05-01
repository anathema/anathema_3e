package net.sf.anathema.hero.charms.model;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.reference.CharmName;

public interface CharmMap {

  Charm getCharmById(CharmName charmId);

  boolean exists(CharmName charmId);
}