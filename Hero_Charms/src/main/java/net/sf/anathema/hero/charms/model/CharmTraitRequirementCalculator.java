package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.charms.model.special.prerequisite.IPrerequisiteModifyingCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteModifyingCharms;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class CharmTraitRequirementCalculator {
	private final PrerequisiteModifyingCharms prerequisiteModifyingCharms;
  private final SpecialCharmLearnArbitrator learnArbitrator;
  private final TraitStateFetcher stateFetcher;
	
	public CharmTraitRequirementCalculator(PrerequisiteModifyingCharms charms,
			SpecialCharmLearnArbitrator learnArbitrator,
			TraitStateFetcher stateFetcher) {
		this.prerequisiteModifyingCharms = charms;
		this.learnArbitrator = learnArbitrator;
		this.stateFetcher = stateFetcher;
	}
	
	public int calculateMinimum(Charm charm, TraitType trait, int baseValue) {
		int requiredValue = baseValue;
		
		if (trait.equals(OtherTraitType.Essence) &&
				stateFetcher.fetch(new TraitTypeUtils().getPrimaryTraitType(charm)) == TraitStateType.Supernal) {
			if (baseValue <= 5) {
				return 1;
			}
		}
		
		for (IPrerequisiteModifyingCharm modifier : prerequisiteModifyingCharms.getPrerequisiteModifyingCharms()) {
      if (learnArbitrator.isLearned(modifier.getCharmName())) {
        requiredValue = modifier.modifyRequiredValue(charm, requiredValue);
      }
    }
		
		return requiredValue;
	}
}
