package net.sf.anathema.hero.charms.model;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.magic.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.hero.charms.model.special.learning.multilearn.TraitRequirementChecker;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;

public class CharmTraitRequirementChecker implements TraitRequirementChecker {
  private final CharmTraitRequirementCalculator calculator;
  private final TraitMap traitMap;

  public CharmTraitRequirementChecker(CharmTraitRequirementCalculator calculator, TraitMap traitMap) {
    this.calculator = calculator;
    this.traitMap = traitMap;
  }

  public boolean areTraitMinimumsSatisfied(Charm charm) {
    boolean[] satisfied = new boolean[]{true};
    charm.getPrerequisites().forEachTraitPrerequisite(prerequisite -> {
      if (!isMinimumSatisfied(charm, prerequisite)) {
        satisfied[0] = false;
      }
    });
    return satisfied[0];
  }

  @Override
  public boolean isMinimumSatisfied(Charm charm, TraitPrerequisite prerequisite) {
    TraitType traitType = new TraitTypeUtils().getTraitTypeFor(prerequisite);
    if (!(traitMap.contains(traitType))) {
      return false;
    }
    Trait trait = traitMap.getTrait(traitType);
    int requiredValue = calculator.calculateMinimum(charm, traitType, prerequisite.minimalValue);
    return trait.getCurrentValue() >= requiredValue;
  }
}