package net.sf.anathema.hero.charms.display.prerequisites;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.charms.display.view.NodeIds.getNodeId;
import static net.sf.anathema.hero.charms.display.view.NodeIds.getNodeIdForAnyOneTrait;

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
  public void requiresMagicAttributesFromTree(TreeReference tree,
                                              MagicAttribute attribute, int count) {
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

  @Override
  public void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category, int count,
                                     int minimumEssence) {
    nodeIds.add(getNodeId(traits, category, count, minimumEssence));
  }

  @Override
  public void requiresCharmsOfAnyOneTrait(int threshold) {
    nodeIds.add(getNodeIdForAnyOneTrait(threshold));
  }
}
