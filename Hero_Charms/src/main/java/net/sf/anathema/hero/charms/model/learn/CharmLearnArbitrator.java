package net.sf.anathema.hero.charms.model.learn;

import java.util.List;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.reference.CategoryReference;
import net.sf.anathema.charm.data.reference.TreeReference;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.magic.data.attribute.MagicAttribute;

public interface CharmLearnArbitrator {

  boolean isLearned(Charm charm);
  
  boolean hasLearnedThresholdCharmsWithKeywordFromTree(TreeReference tree, MagicAttribute attribute, int threshold);
  
  boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute, int threshold);
  
  boolean hasLearnedThresholdCharmsOfTrait(List<TraitType> traits, CategoryReference category, int threshold, int minimumEssence);

	boolean hasLearnedThresholdCharmsOfAnyOneTrait(int threshold);
}