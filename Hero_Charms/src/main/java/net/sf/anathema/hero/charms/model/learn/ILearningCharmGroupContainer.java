package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

public interface ILearningCharmGroupContainer {

  LearningCharmTree getLearningCharmGroup(Charm charm);
}