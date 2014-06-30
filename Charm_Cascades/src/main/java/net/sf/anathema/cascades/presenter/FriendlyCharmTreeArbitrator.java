package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.display.presenter.CharmTreeArbitrator;
import net.sf.anathema.hero.charms.model.CharmTree;
import net.sf.anathema.hero.magic.charm.Charm;

public class FriendlyCharmTreeArbitrator implements CharmTreeArbitrator {

  @Override
  public Charm[] getCharms(CharmTree charmGroup) {
    return charmGroup.getAllCharms();
  }
}