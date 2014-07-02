package net.sf.anathema.hero.charms.model.learn.prerequisites;

import net.sf.anathema.charm.old.attribute.MagicAttribute;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.prerequisite.CharmPrerequisiteVisitor;

public class IsSatisfied implements CharmPrerequisiteVisitor {

  private ICharmLearnArbitrator arbitrator;
  public boolean satisfied = true;

  public IsSatisfied(ICharmLearnArbitrator learnArbitrator) {
    this.arbitrator = learnArbitrator;
  }

  @Override
  public void requiresMagicAttributes(MagicAttribute attribute, int count) {
    this.satisfied = arbitrator.hasLearnedThresholdCharmsWithKeyword(attribute, count);
  }

  @Override
  public void requiresCharm(Charm prerequisite) {
    this.satisfied = arbitrator.isLearned(prerequisite);
  }

  @Override
  public void requiresCharmFromSelection(Charm[] prerequisites, int threshold) {
    int known = 0;
    for (Charm charm : prerequisites) {
      if (arbitrator.isLearned(charm)) {
        known++;
      }
      if (known >= threshold) {
        this.satisfied = true;
      }
    }
    this.satisfied =false;
  }
}
