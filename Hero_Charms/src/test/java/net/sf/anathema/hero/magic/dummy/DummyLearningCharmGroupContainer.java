package net.sf.anathema.hero.magic.dummy;

import net.sf.anathema.hero.charms.model.learn.LearningCharmTree;
import net.sf.anathema.hero.charms.model.learn.LearningCharmTreeImpl;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.learn.ILearningCharmGroupContainer;

public class DummyLearningCharmGroupContainer implements ILearningCharmGroupContainer {

  private LearningCharmTree[] groups;

  public void setLearningCharmGroups(LearningCharmTree[] groups) {
    this.groups = groups;
  }

  @Override
  public LearningCharmTree getLearningCharmGroup(Charm charm) {
    for (LearningCharmTree group : groups) {
      if (charm.getGroupId().equals(group.getId())) {
        return group;
      }
    }
    return null;
  }

  public void setLearningCharmGroup(LearningCharmTreeImpl learningCharmGroup) {
    setLearningCharmGroups(new LearningCharmTree[]{learningCharmGroup});
  }
}