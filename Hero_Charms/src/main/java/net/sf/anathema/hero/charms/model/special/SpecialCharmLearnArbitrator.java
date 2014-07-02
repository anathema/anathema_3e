package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.charm.data.reference.CharmName;

public interface SpecialCharmLearnArbitrator {

  boolean isLearned(CharmName charmId);
}