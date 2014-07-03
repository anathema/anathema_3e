package net.sf.anathema.hero.magic.dummy;

import net.sf.anathema.hero.charms.model.learn.ILearningCharmGroupContainer;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTreeImpl;
import net.sf.anathema.charm.data.Charm;

public class DummyLearningCharmGroupContainer implements ILearningCharmGroupContainer {

  private LearningCharmTree[] groups;

  public void setLearningCharmGroups(LearningCharmTree[] groups) {
    this.groups = groups;
  }

  @Override
  public LearningCharmTree getLearningCharmGroup(Charm charm) {
    for (LearningCharmTree group : groups) {
      if (charm.getTreeReference().equals(group.getReference())) {
        return group;
      }
    }
    return null;
  }

  public void setLearningCharmGroup(LearningCharmTreeImpl learningCharmGroup) {
    setLearningCharmGroups(new LearningCharmTree[]{learningCharmGroup});
  }
}