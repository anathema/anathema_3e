package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.CharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.CharmLearnableArbitrator;

public interface IExtendedCharmLearnableArbitrator extends CharmLearnArbitrator, CharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(Charm charm);
}