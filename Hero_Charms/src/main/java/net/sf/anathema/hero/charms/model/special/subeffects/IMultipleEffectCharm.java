package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;

public interface IMultipleEffectCharm extends CharmSpecialLearning {

  SubEffects buildSubEffects(CharmSpecialist specialist, CharmLearnableArbitrator arbitrator, Charm charm);
}