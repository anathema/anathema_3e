package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.hero.magic.charm.Charm;

import java.util.Arrays;

public class DirectGroupCharmPrerequisite implements CharmPrerequisite {

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

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DirectGroupCharmPrerequisite) {
      DirectGroupCharmPrerequisite prerequisite = (DirectGroupCharmPrerequisite) obj;
      return Arrays.deepEquals(prerequisites, prerequisite.prerequisites) && prerequisite.threshold == threshold;
    }
    return false;
  }
}
