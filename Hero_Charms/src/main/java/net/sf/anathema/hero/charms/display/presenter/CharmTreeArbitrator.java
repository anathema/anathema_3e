package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.magic.charm.Charm;

public interface CharmTreeArbitrator {

  Charm[] getCharms(CharmTree charmGroup);
}