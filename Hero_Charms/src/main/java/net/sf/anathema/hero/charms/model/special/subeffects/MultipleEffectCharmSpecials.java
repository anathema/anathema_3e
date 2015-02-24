package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.hero.charms.model.special.CharmSpecialLearningModel;

public interface MultipleEffectCharmSpecials extends CharmSpecialLearningModel {
  Iterable<SubEffect> getEffects();

  SubEffect getEffectById(String id);
}