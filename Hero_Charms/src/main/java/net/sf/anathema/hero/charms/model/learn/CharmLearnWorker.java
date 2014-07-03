package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.charm.data.Charm;

public interface CharmLearnWorker extends CharmLearnArbitrator {

  void forget(Charm charm);
}