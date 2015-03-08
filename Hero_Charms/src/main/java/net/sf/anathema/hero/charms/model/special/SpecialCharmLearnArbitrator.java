package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.magic.data.reference.CharmName;

public interface SpecialCharmLearnArbitrator {

  boolean isLearned(CharmName charmId);
}