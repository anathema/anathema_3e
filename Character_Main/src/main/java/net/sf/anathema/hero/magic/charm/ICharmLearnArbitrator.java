package net.sf.anathema.hero.magic.charm;

import net.sf.anathema.hero.magic.basic.attribute.MagicAttribute;

public interface ICharmLearnArbitrator {

  boolean isLearned(Charm charm);
  
  boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold);
}