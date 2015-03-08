package net.sf.anathema.hero.charms.display.node;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.magic.data.prerequisite.RequiredTraitType;
import net.sf.anathema.magic.data.reference.CategoryReference;
import net.sf.anathema.magic.data.reference.TreeReference;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.hero.charms.display.view.NodeIds;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.magic.data.prerequisite.ProcessProcessor.process;
import static net.sf.anathema.graph.nodes.NodeFactory.createChildlessNode;

// todo (sandra) entstatifizieren
public class CharmNodeBuilder {

  public static void buildNodes(Collection<Charm> groupCharms, Map<String, IIdentifiedRegularNode> charmNodesById) {
    for (Charm charm : groupCharms) {
      IIdentifiedRegularNode node = createChildlessNode(charm.getName().text);
      charmNodesById.put(charm.getName().text, node);
    }
    for (Charm charm : groupCharms) {
      charm.getPrerequisites().forEachCharmPrerequisite(process(new PrerequisiteProcessor() {
        @Override
        public void requiresMagicAttributes(MagicAttribute attribute, int count) {
          String nodeIds = NodeIds.getNodeId(attribute, count);
          IIdentifiedRegularNode parentNode = createChildlessNode(nodeIds);
          charmNodesById.put(nodeIds, parentNode);
        }

        @Override
        public void requiresMagicAttributesFromTree(TreeReference tree,
                                                    MagicAttribute attribute, int count) {
          String nodeIds = NodeIds.getNodeId(attribute, count);
          IIdentifiedRegularNode parentNode = createChildlessNode(nodeIds);
          charmNodesById.put(nodeIds, parentNode);
        }

        @Override
        public void requiresCharm(Charm prerequisite) {
          handleDirectParent(groupCharms, charmNodesById, prerequisite);
        }

        @Override
        public void requiresCharmFromSelection(Charm[] prerequisites, int count) {
          for (Charm prerequisite : prerequisites) {
            handleDirectParent(groupCharms, charmNodesById, prerequisite);
          }
        }

        @Override
        public void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category,
                                           int count, int minimumEssence) {
          String nodeIds = NodeIds.getNodeId(traits, category, count, minimumEssence);
          IIdentifiedRegularNode parentNode = createChildlessNode(nodeIds);
          charmNodesById.put(nodeIds, parentNode);
        }

        @Override
        public void requiresCharmsOfAnyOneTrait(int count) {
          String nodeIds = NodeIds.getNodeIdForAnyOneTrait(count);
          IIdentifiedRegularNode parentNode = createChildlessNode(nodeIds);
          charmNodesById.put(nodeIds, parentNode);
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