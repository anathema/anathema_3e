package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.hero.traits.model.Trait;

public class TraitRatingCostCalculator {

  public static int getTraitRatingCost(Trait trait, CurrentRatingCost ratingCosts) {
    return getTraitRatingCosts(trait.getCreationValue(), trait.getExperiencedValue(), ratingCosts);
  }

  public static int getTraitRatingCosts(int valueToAchieveWithoutCost, int valueToPayFor, CurrentRatingCost ratingCosts) {
    int traitCosts = 0;
    int currentRating = valueToAchieveWithoutCost;
    while (currentRating < valueToPayFor) {
      traitCosts += ratingCosts.getRatingCosts(currentRating);
      currentRating++;
    }
    return traitCosts;
  }
}