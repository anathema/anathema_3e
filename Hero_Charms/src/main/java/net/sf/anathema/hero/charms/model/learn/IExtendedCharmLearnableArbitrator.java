package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.hero.magic.charm.Charm;

public interface IExtendedCharmLearnableArbitrator extends CharmLearnArbitrator, CharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(Charm charm);
}