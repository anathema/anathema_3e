package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;

public interface TraitRequirementChecker {

  boolean isMinimumSatisfied(Charm charm, TraitPrerequisite requirement);
}