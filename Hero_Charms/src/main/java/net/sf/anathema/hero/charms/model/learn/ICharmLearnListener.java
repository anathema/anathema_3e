package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

public interface ICharmLearnListener {

  void charmLearned(Charm charm);

  void charmForgotten(Charm charm);

  void charmNotLearnable(Charm charm);

  void charmNotForgettable(Charm charm);

  void recalculateRequested();
}