package net.sf.anathema.charm.data.prerequisite;

import java.util.function.Consumer;

public class AcceptVisitor implements Consumer<CharmPrerequisite> {

  public static AcceptVisitor acceptVisitor(PrerequisiteVisitor visitor) {
    return new AcceptVisitor(visitor);
  }

  private PrerequisiteVisitor visitor;

  public AcceptVisitor(PrerequisiteVisitor visitor) {
    this.visitor = visitor;
  }

  @Override
  public void accept(CharmPrerequisite charmPrerequisite) {
    charmPrerequisite.accept(visitor);
  }
}
