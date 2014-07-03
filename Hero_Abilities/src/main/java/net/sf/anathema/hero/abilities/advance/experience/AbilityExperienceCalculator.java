package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.traits.model.Trait;

public class AbilityExperienceCalculator {

  private AbilityExperienceData experienceData;

  public AbilityExperienceCalculator(AbilityExperienceData experienceData) {
    this.experienceData = experienceData;
  }

  public int getAbilityCosts(Trait ability, final boolean favored) {
    return getTraitRatingCosts(ability, experienceData.getAbilityCosts(favored));
  }

  private int getTraitRatingCosts(Trait trait, CurrentRatingCost ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCost(trait, ratingCosts);
  }
}
