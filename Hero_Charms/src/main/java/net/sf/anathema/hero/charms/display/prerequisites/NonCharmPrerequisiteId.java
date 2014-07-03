package net.sf.anathema.hero.charms.display.prerequisites;

import net.sf.anathema.magic.attribute.MagicAttribute;
import net.sf.anathema.hero.charms.display.view.NodeIds;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteProcessor;

public class NonCharmPrerequisiteId implements PrerequisiteProcessor {

  public String id = null;

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    this.id = NodeIds.getNodeId(attribute, count);
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    throw new UnsupportedOperationException("This is a direct charm prerequisite.");
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    throw new UnsupportedOperationException("This is a direct charm prerequisite.");
  }
}
