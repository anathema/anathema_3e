package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.CharmTree;

public interface CharmTreeArbitrator {

  Charm[] filterAvailableCharms(CharmTree charmGroup);
}