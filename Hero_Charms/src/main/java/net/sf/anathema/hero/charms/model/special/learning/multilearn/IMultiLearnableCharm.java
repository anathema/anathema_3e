package net.sf.anathema.hero.charms.model.special.learning.multilearn;

import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;

public interface IMultiLearnableCharm extends CharmSpecialLearning {

  int getAbsoluteLearnLimit();
  
  int getMinimumLearnCount(LearnRangeContext learnRangeContext);

  int getMaximumLearnCount(LearnRangeContext learnRangeContext);
}