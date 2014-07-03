package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.BasicLearnCharmTree;

public interface ICharmLearnStrategy {

  boolean isUnlearnable(BasicLearnCharmTree group, Charm charm);

  boolean isLearned(BasicLearnCharmTree group, Charm charm);

  void toggleLearned(BasicLearnCharmTree group, Charm charm);
}