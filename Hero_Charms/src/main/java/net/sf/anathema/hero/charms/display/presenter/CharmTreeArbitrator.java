package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.charm.data.Charm;

public interface CharmTreeArbitrator {

  Charm[] filterAvailableCharms(CharmTree charmGroup);
}