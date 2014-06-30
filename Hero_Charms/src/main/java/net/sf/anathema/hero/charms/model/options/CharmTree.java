package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.ICharmLearnableArbitrator;

import java.util.List;

public interface CharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {
  // nothing to do
}