package net.sf.anathema.hero.charms.display.prerequisites;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.charms.display.view.NodeIds.getNodeId;

public class ConcreteCharmParentIds implements PrerequisiteProcessor {

  public static List<String> collectNodeIdsOfRenderingParents(Charm charm) {
    ConcreteCharmParentIds renderingParents = new ConcreteCharmParentIds();
    charm.getPrerequisites().forEachCharmPrerequisite(prerequisite -> prerequisite.process(renderingParents));
    return renderingParents.nodeIds;
  }

  public List<String> nodeIds = new ArrayList<>();

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    nodeIds.add(getNodeId(attribute, count));
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    nodeIds.add(getNodeId(prerequisite));
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    for (Charm prerequisite : prerequisites) {
      nodeIds.add(getNodeId(prerequisite));
    }
  }
}
