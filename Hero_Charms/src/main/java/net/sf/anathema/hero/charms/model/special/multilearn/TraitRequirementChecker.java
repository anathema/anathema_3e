package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.magic.charm.prerequisite.TraitPrerequisite;

public interface TraitRequirementChecker {

  boolean isMinimumSatisfied(Charm charm, TraitPrerequisite requirement);
}