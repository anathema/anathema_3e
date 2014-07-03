package net.sf.anathema.hero.magic.charm.prerequisite;

import java.util.function.Consumer;

public class ProcessProcessor implements Consumer<CharmPrerequisite> {

  public static ProcessProcessor acceptVisitor(PrerequisiteProcessor visitor) {
    return new ProcessProcessor(visitor);
  }

  private PrerequisiteProcessor visitor;

  public ProcessProcessor(PrerequisiteProcessor visitor) {
    this.visitor = visitor;
  }

  @Override
  public void accept(CharmPrerequisite charmPrerequisite) {
    charmPrerequisite.process(visitor);
  }
}
