package net.sf.anathema.charm.data.prerequisite;

import java.util.List;

import net.sf.anathema.charm.data.Charm;

public class SpecificGroupCharmPrerequisite implements CharmPrerequisite {
  private final int threshold;
  private List<Charm> prerequisites;


  public SpecificGroupCharmPrerequisite(List<Charm> charms, int threshold) {
    this.prerequisites = charms;
    this.threshold = threshold;
  }

  @Override
  public void process(PrerequisiteProcessor processor) {
    Charm[] prerequisitesArray = prerequisites.toArray(new Charm[prerequisites.size()]);
    processor.requiresCharmFromSelection(prerequisitesArray, threshold);
  }

  @Override
  public void accept(PrerequisiteVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public boolean isSpecific() {
    return true;
  }
}
