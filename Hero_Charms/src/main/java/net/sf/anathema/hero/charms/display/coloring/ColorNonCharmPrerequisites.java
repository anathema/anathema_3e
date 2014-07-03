package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.magic.charm.prerequisite.AttributeKnownCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.DirectGroupCharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.PrerequisiteVisitor;
import net.sf.anathema.hero.magic.charm.prerequisite.SimpleCharmPrerequisite;

public class ColorNonCharmPrerequisites implements PrerequisiteVisitor {
  private CharmColoring coloring;

  public ColorNonCharmPrerequisites(CharmColoring coloring) {
    this.coloring = coloring;
  }

  @Override
  public void visit(AttributeKnownCharmPrerequisite prerequisite) {
    coloring.colorNonCharmPrerequisite(prerequisite);
  }

  @Override
  public void visit(DirectGroupCharmPrerequisite prerequisite) {
    // nothing to do
  }

  @Override
  public void visit(SimpleCharmPrerequisite prerequisite) {
    // nothing to do
  }
}
