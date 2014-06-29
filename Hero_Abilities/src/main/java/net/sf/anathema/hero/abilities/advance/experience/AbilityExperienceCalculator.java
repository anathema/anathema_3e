package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.template.experience.CurrentRatingCost;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;

public class AbilityExperienceCalculator {

  private final IExperiencePointCosts costs;

  public AbilityExperienceCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  public int getAbilityCosts(Trait ability, final boolean favored) {
    return getTraitRatingCosts(ability, costs.getAbilityCosts(favored));
  }

  private int getTraitRatingCosts(Trait trait, CurrentRatingCost ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCost(trait, ratingCosts);
  }
}
