package net.sf.anathema.hero.magic.charm.prerequisite;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.UnlinkedCharmMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectGroupCharmPrerequisite implements CharmPrerequisite {

  private final int threshold;
  private final CharmName[] prerequisiteIds;
  private Charm[] prerequisites;


  public DirectGroupCharmPrerequisite(CharmName[] charms, int threshold) {
    this.prerequisiteIds = charms;
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
  public void link(UnlinkedCharmMap charmsById) {
    List<Charm> prerequisites = new ArrayList<>();
    for (CharmName id : prerequisiteIds) {
      prerequisites.add(charmsById.get(id));
    }
    this.prerequisites = prerequisites.toArray(new Charm[prerequisites.size()]);
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
