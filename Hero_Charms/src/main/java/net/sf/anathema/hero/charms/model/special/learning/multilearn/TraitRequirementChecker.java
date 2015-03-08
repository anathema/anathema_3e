package net.sf.anathema.hero.charms.model.special.learning.multilearn;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;

public interface TraitRequirementChecker {

  boolean isMinimumSatisfied(Charm charm, TraitPrerequisite requirement);
}