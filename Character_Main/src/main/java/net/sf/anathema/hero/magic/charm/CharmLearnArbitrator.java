package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.charm.old.attribute.MagicAttribute;

public interface CharmLearnArbitrator {

  boolean isLearned(Charm charm);
  
  boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold);
}