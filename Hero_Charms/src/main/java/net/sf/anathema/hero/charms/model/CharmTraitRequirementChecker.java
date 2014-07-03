package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.charms.model.special.multilearn.TraitRequirementChecker;
import net.sf.anathema.hero.charms.model.special.prerequisite.IPrerequisiteModifyingCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteModifyingCharms;
import net.sf.anathema.hero.magic.charm.Charm;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.ValuedTraitType;

public class CharmTraitRequirementChecker implements TraitRequirementChecker {
  private final PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private TraitMap traitMap;
  private final SpecialCharmLearnArbitrator learnArbitrator;

  public CharmTraitRequirementChecker(PrerequisiteModifyingCharms prerequisiteModifyingCharms, TraitMap traitMap,
                                      SpecialCharmLearnArbitrator learnArbitrator) {
    this.prerequisiteModifyingCharms = prerequisiteModifyingCharms;
    this.traitMap = traitMap;
    this.learnArbitrator = learnArbitrator;
  }

  public boolean areTraitMinimumsSatisfied(Charm charm) {
    for (ValuedTraitType prerequisite : charm.getPrerequisites().getTraitPrerequisites()) {
      if (!isMinimumSatisfied(charm, prerequisite)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isMinimumSatisfied(Charm charm, ValuedTraitType prerequisite) {
    Trait actualTrait = traitMap.getTrait(prerequisite.getType());
    if (actualTrait == null) {
      return false;
    }
    int requiredValue = prerequisite.getCurrentValue();
    for (IPrerequisiteModifyingCharm modifier : prerequisiteModifyingCharms.getPrerequisiteModifyingCharms()) {
      if (learnArbitrator.isLearned(modifier.getCharmName())) {
        requiredValue = modifier.modifyRequiredValue(charm, requiredValue);
      }
    }
    return actualTrait.getCurrentValue() >= requiredValue;
  }
}