package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.hero.charms.model.special.learning.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;

public interface ICharmSpecialLearningVisitor {
  void visitMultiLearnableCharm(IMultiLearnableCharm charm);

  void visitSubEffectCharm(ISubEffectCharm charm);

  void visitMultipleEffectCharm(IMultipleEffectCharm charm);
}