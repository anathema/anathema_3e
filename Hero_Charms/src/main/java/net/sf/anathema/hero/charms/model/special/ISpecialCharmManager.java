package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.LearningModel;

public interface ISpecialCharmManager {

  CharmSpecialLearningModel getSpecialCharmConfiguration(Charm charm);

  void registerSpecialCharmConfiguration(CharmSpecialLearning specialCharm, Charm charm, LearningModel learningModel);
}