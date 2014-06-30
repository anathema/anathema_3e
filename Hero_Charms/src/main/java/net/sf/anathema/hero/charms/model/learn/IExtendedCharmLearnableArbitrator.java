package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;

public interface IExtendedCharmLearnableArbitrator extends ICharmLearnArbitrator, ICharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(Charm charm);
}