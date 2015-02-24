package net.sf.anathema.hero.charms.compiler.json;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.PrerequisiteProcessorAdapter;

public class LinkParentsToChild extends PrerequisiteProcessorAdapter {
  private final CharmImpl child;

  public LinkParentsToChild(CharmImpl child) {
    this.child = child;
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    ((CharmImpl) prerequisite).addChild(child);
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int count) {
    for (Charm prerequisite : prerequisites) {
      requiresCharm(prerequisite);
    }
  }
}