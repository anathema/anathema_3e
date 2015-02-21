package net.sf.anathema.hero.charms.display.prerequisites;

import java.util.List;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessor;
import net.sf.anathema.charm.data.prerequisite.RequiredTraitType;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.charms.display.view.NodeIds;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

public class NonCharmPrerequisiteId implements PrerequisiteProcessor {

  public String id = null;

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    this.id = NodeIds.getNodeId(attribute, count);
  }

  @Override
  public void requiresMagicAttributesFromTree(TreeReference tree,
                                              MagicAttribute attribute, int count) {
    this.id = NodeIds.getNodeId(attribute, count);
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    throw new UnsupportedOperationException("This is a direct charm prerequisite.");
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int count) {
    throw new UnsupportedOperationException("This is a direct charm prerequisite.");
  }

  @Override
  public void requiresCharmsOfTraits(List<RequiredTraitType> traits, CategoryReference category, int count,
                                     int minimumEssence) {
    this.id = NodeIds.getNodeId(traits, category, count, minimumEssence);
  }

  @Override
  public void requiresCharmsOfAnyOneTrait(int count) {
    this.id = NodeIds.getNodeIdForAnyOneTrait(count);
  }
}
