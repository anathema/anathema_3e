package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

public interface LearningModel extends BasicLearningModel {

  void toggleLearned(Charm charm);

  void learnCharm(Charm charm, boolean experienced);

  void forgetCharm(Charm child, boolean experienced);

  void learnCharmNoParents(Charm charm, boolean experienced, boolean announce);

  Charms getCharmsLearnedOnCreation();

  Charms getCharmsLearnedWithExperience();

  Charms getCharmsLearnedEitherWay();

  Charms getCurrentlyLearnedCharms();

  boolean isForgettable(Charm charm);

  void addCharmLearnListener(ICharmLearnListener listener);

  void fireRecalculateRequested();
}
