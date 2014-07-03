package net.sf.anathema.hero.charms.display.node;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.hero.charms.display.view.NodeIds;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteProcessor;

import java.util.Collection;
import java.util.Map;

import static net.sf.anathema.graph.nodes.NodeFactory.createChildlessNode;
import static net.sf.anathema.hero.magic.charm.prerequisite.ProcessProcessor.acceptVisitor;

// todo (sandra) entstatifizieren
public class CharmNodeBuilder {

  public static void buildNodes(Collection<Charm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (Charm charm : groupCharms) {
      IIdentifiedRegularNode node = createChildlessNode(charm.getName().text);
      charmNodesById.put(charm.getName().text, node);
    }
    for (Charm charm : groupCharms) {
      charm.forEachCharmPrerequisite(acceptVisitor(new PrerequisiteProcessor() {
        @Override
        public void requiresMagicAttributes(MagicAttribute attribute, int count) {
          String nodeIds = NodeIds.getNodeId(attribute, count);
          IIdentifiedRegularNode parentNode = createChildlessNode(nodeIds);
          charmNodesById.put(nodeIds, parentNode);
        }

        @Override
        public void requiresCharm(Charm prerequisite) {
          handleDirectParent(groupCharms, charmNodesById, prerequisite);
        }

        @Override
        public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
           for(Charm prerequisite : prerequisites) {
             handleDirectParent(groupCharms, charmNodesById, prerequisite);
           }
        }
      }));
    }
  }

  private static void handleDirectParent(Collection<Charm> groupCharms,
                                         Map<String, IIdentifiedRegularNode> charmNodesById, Charm parentCharm) {
    if (!groupCharms.contains(parentCharm)) {
      String nodeId = NodeIds.getNodeId(parentCharm);
      IIdentifiedRegularNode parentNode = charmNodesById.get(nodeId);
      if (parentNode == null) {
        parentNode = createChildlessNode(nodeId);
        parentNode.setLowerToChildren(true);
        charmNodesById.put(nodeId, parentNode);
      } else {
        parentNode.setLowerToChildren(false);
      }
    }
  }
}