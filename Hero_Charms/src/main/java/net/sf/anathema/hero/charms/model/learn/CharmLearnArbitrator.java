package net.sf.anathema.hero.charms.model.learn;

import java.util.List;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

public interface CharmLearnArbitrator {

  boolean isLearned(Charm charm);
  
  boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold);
  
  boolean hasLearnedThresholdCharmsOfTrait(List<TraitType> traits, int threshold, int minimumEssence);

	boolean hasLearnedThresholdCharmsOfAnyOneTrait(int threshold);
}