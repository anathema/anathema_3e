package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

public interface CharmLearnArbitrator {

  boolean isLearned(Charm charm);
  
  boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold);
}