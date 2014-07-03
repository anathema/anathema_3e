package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.learn.CharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

public interface IMultipleEffectCharm extends ISpecialCharm {

  SubEffects buildSubEffects(CharmSpecialist specialist, CharmLearnableArbitrator arbitrator, Charm charm);
}