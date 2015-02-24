package net.sf.anathema.hero.charms.model.special.learning.multilearn;

import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;
import net.sf.anathema.hero.traits.model.Trait;

public interface MultiLearnCharmSpecials extends CharmSpecialLearningModel {

  Trait getCategory();

  void setCurrentLearnCount(int newValue);
}