package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.CharmTree;

import java.util.Collection;

public class FriendlyCharmTreeArbitrator implements CharmTreeArbitrator {

  @Override
  public Collection<Charm> filterAvailableCharms(CharmTree charmGroup) {
    return charmGroup.getAllCharms();
  }
}