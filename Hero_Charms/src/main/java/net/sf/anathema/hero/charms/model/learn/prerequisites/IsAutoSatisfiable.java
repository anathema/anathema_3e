package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisite;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisiteVisitor;

public class IsAutoSatisfiable implements CharmPrerequisiteVisitor {

  public static boolean isAutoSatisfiable(CharmPrerequisite prerequisite, ICharmLearnableArbitrator arbitrator) {
    IsAutoSatisfiable satisfied = new IsAutoSatisfiable(arbitrator);
    prerequisite.accept(satisfied);
    return satisfied.satisfiable;
  }

  private final ICharmLearnableArbitrator arbitrator;
  public boolean satisfiable = true;

  public IsAutoSatisfiable(ICharmLearnableArbitrator learnArbitrator) {
    this.arbitrator = learnArbitrator;
  }

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    this.satisfiable = false;
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    this.satisfiable = arbitrator.isLearnable(prerequisite);
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    int knowable = 0;
    for (Charm charm : prerequisites) {
      if (arbitrator.isLearnable(charm)) {
        knowable++;
      }
      if (knowable >= threshold) {
        this.satisfiable = true;
      }
    }
    this.satisfiable = false;
  }
}
