package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.prerequisite.TraitPrerequisite;
import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.charms.model.special.multilearn.TraitRequirementChecker;
import net.sf.anathema.hero.charms.model.special.prerequisite.IPrerequisiteModifyingCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteModifyingCharms;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;

public class CharmTraitRequirementChecker implements TraitRequirementChecker {
  private final CharmTraitRequirementCalculator calculator;
  private TraitMap traitMap;

  public CharmTraitRequirementChecker(CharmTraitRequirementCalculator calculator, TraitMap traitMap) {
    this.calculator = calculator;
    this.traitMap = traitMap;
  }

  public boolean areTraitMinimumsSatisfied(Charm charm) {
    for (TraitPrerequisite prerequisite : charm.getPrerequisites().getTraitPrerequisites()) {
      if (!isMinimumSatisfied(charm, prerequisite)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isMinimumSatisfied(Charm charm, TraitPrerequisite prerequisite) {
    TraitType traitType = new TraitTypeUtils().getTraitTypeFor(prerequisite);
    Trait actualTrait = traitMap.getTrait(traitType);
    if (actualTrait == null) {
      return false;
    }
    int requiredValue = calculator.calculateMinimum(charm, traitType, prerequisite.minimalValue);
    
    return actualTrait.getCurrentValue() >= requiredValue;
  }
}