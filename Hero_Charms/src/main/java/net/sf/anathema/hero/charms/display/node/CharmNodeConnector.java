package net.sf.anathema.hero.charms.display.node;

import java.util.Collection;
import java.util.Map;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.IndirectCharmPrerequisite;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;

public class CharmNodeConnector {

  public static void connectNodes(Collection<Charm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (Charm charm : groupCharms) {
      IIdentifiedRegularNode childNode = charmNodesById.get(charm.getName().text);
      for (Charm parentCharm : charm.getRenderingPrerequisiteCharms()) {
        IIdentifiedRegularNode parentNode = charmNodesById.get(parentCharm.getName().text);
        connectNodes(childNode, parentNode);
      }
      for (IndirectCharmPrerequisite requirement : charm.getPrerequisitesOfType(IndirectCharmPrerequisite.class)) {
        IIdentifiedRegularNode parentNode = charmNodesById.get(requirement.getStringLabel());
        connectNodes(childNode, parentNode);
      }
    }
  }

  private static void connectNodes(IIdentifiedRegularNode childNode, IIdentifiedRegularNode parentNode) {
    parentNode.addChild(childNode);
    childNode.addParent(parentNode);
  }
}