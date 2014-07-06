package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.CharmTree;

import java.util.Collection;

public interface CharmTreeArbitrator {

  Collection<Charm> filterAvailableCharms(CharmTree charmGroup);
}