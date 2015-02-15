package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.hero.traits.model.Trait;

public class TraitRatingCostCalculator {

  public static int getTraitRatingCost(Trait trait, RatingCost ratingCosts) {
    return getTraitRatingCosts(trait.getCreationValue(), trait.getExperiencedValue(), ratingCosts);
  }

  private static int getTraitRatingCosts(int valueToAchieveWithoutCost, int valueToPayFor, RatingCost ratingCosts) {
    int traitCosts = 0;
    int currentRating = valueToAchieveWithoutCost;
    while (currentRating < valueToPayFor) {
      traitCosts += ratingCosts.getRatingCosts(currentRating);
      currentRating++;
    }
    return traitCosts;
  }
}