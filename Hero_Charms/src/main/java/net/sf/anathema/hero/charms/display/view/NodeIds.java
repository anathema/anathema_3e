package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.charm.data.reference.CharmName;

public class NodeIds {

  public static CharmName toCharmName(String nodeId) {
    return new CharmName(nodeId);
  }
}
