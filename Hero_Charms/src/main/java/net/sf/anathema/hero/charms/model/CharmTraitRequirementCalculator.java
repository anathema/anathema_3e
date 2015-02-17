package net.sf.anathema.hero.charms.model;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

import static net.sf.anathema.charm.data.CharmAttributeList.IGNORES_SUPERNAL;
import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;

public class CharmTraitRequirementCalculator {
  private final TraitStateFetcher stateFetcher;

  public CharmTraitRequirementCalculator(TraitStateFetcher stateFetcher) {
    this.stateFetcher = stateFetcher;
  }

  public int calculateMinimum(Charm charm, TraitType trait, int baseValue) {
    boolean requiredTraitIsEssence = trait.equals(OtherTraitType.Essence);
    boolean supernalApplies = doesSupernalApply(charm);
    if (requiredTraitIsEssence && supernalApplies) {
      if (baseValue <= 5) {
        return 1;
      }
    }
    return baseValue;
  }

  private boolean doesSupernalApply(Charm charm) {
    if (charm.hasAttribute(IGNORES_SUPERNAL)) {
      return false;
    }
    TraitType primaryTrait = new TraitTypeUtils().getPrimaryTraitType(charm);
    return stateFetcher.fetch(primaryTrait).countsAs(Supernal);
  }
}