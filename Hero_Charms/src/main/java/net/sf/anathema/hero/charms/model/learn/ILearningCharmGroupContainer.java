package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.hero.magic.charm.Charm;

public interface ILearningCharmGroupContainer {

  ILearningCharmGroup getLearningCharmGroup(Charm charm);
}