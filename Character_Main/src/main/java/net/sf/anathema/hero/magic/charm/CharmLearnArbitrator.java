package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.magic.attribute.MagicAttribute;

public interface CharmLearnArbitrator {

  boolean isLearned(Charm charm);
  
  boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold);
}