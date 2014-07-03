package net.sf.anathema.hero.charms.display.node;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteProcessor;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.charms.display.view.NodeIds.getNodeId;

public class RenderingParentIds implements PrerequisiteProcessor {

  public static List<String> collectNodeIdsOfRenderingParents(Charm charm) {
    RenderingParentIds renderingParents = new RenderingParentIds();
    charm.forEachCharmPrerequisite(charmPrerequisite -> charmPrerequisite.process(renderingParents));
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
