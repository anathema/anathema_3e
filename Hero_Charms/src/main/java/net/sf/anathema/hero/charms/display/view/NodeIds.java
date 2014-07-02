package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;

public class NodeIds {

  public static CharmName toCharmName(String nodeId) {
    return new CharmName(nodeId);
  }

  public static String getNodeId(MagicAttribute attribute, int count) {
    return "Requirement." + attribute.getId() + "." + count;
  }

  public static String getNodeId(Charm prerequisite) {
    return prerequisite.getName().text;
  }
}
