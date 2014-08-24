package net.sf.anathema.hero.charms.model;

import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;
import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.special.SpecialCharmLearnArbitrator;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class CharmTraitRequirementCalculator {
  private final SpecialCharmLearnArbitrator learnArbitrator;
  private final TraitStateFetcher stateFetcher;

  public CharmTraitRequirementCalculator(SpecialCharmLearnArbitrator learnArbitrator,
                                         TraitStateFetcher stateFetcher) {
    this.learnArbitrator = learnArbitrator;
    this.stateFetcher = stateFetcher;
  }

  public int calculateMinimum(Charm charm, TraitType trait, int baseValue) {
    int requiredValue = baseValue;
    boolean requiredTraitIsEssence = trait.equals(OtherTraitType.Essence);
    boolean primaryTraitIsSupernalAbility = stateFetcher.fetch(new TraitTypeUtils().getPrimaryTraitType(charm)).countsAs(Supernal);
    if (requiredTraitIsEssence && primaryTraitIsSupernalAbility) {
      if (baseValue <= 5) {
        return 1;
      }
    }
    return requiredValue;
  }
}
