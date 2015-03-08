package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;

public interface IMultipleEffectCharm extends CharmSpecialLearning {

  SubEffects buildSubEffects(CharmSpecialist specialist, CharmLearnableArbitrator arbitrator, Charm charm);
}