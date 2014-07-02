package net.sf.anathema.hero.magic.charm.prerequisite;

import java.util.function.Consumer;

public class AcceptVisitor implements Consumer<CharmPrerequisite> {

  public static AcceptVisitor acceptVisitor(CharmPrerequisiteVisitor visitor) {
    return new AcceptVisitor(visitor);
  }

  private CharmPrerequisiteVisitor visitor;

  public AcceptVisitor(CharmPrerequisiteVisitor visitor) {
    this.visitor = visitor;
  }

  @Override
  public void accept(CharmPrerequisite charmPrerequisite) {
    charmPrerequisite.accept(visitor);
  }
}
