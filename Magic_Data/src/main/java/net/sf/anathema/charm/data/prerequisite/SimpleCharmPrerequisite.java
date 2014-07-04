package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.charm.data.Charm;

public class SimpleCharmPrerequisite implements CharmPrerequisite {

  private Charm prerequisite;

  public SimpleCharmPrerequisite(Charm charm) {
    this.prerequisite = charm;
  }

  @Override
  public void process(PrerequisiteProcessor processor) {
    processor.requiresCharm(prerequisite);
  }

  @Override
  public void accept(PrerequisiteVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof SimpleCharmPrerequisite) {
      SimpleCharmPrerequisite prerequisite = (SimpleCharmPrerequisite) obj;
      return prerequisite.prerequisite.equals(prerequisite);
    }
    return false;
  }
}