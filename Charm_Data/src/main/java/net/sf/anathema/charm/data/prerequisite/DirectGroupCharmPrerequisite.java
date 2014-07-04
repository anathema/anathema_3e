package net.sf.anathema.charm.data.prerequisite;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.library.lang.ReflectionEqualsObject;

public class DirectGroupCharmPrerequisite extends ReflectionEqualsObject implements CharmPrerequisite {

  private final int threshold;
  private Charm[] prerequisites;


  public DirectGroupCharmPrerequisite(Charm[] charms, int threshold) {
    this.prerequisites = charms;
    this.threshold = threshold;
  }

  @Override
  public void process(PrerequisiteProcessor processor) {
    processor.requiresCharmFromSelection(prerequisites, threshold);
  }

  @Override
  public void accept(PrerequisiteVisitor visitor) {
    visitor.visit(this);
  }
}
