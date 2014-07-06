package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

public interface ICharmLearnStrategy {

  boolean isForgettable(BasicLearningModel group, Charm charm);

  boolean isLearned(BasicLearningModel group, Charm charm);

  void toggleLearned(BasicLearningModel group, Charm charm);
}