package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.hero.traits.model.Trait;

public class TraitRatingCostCalculator {

  public static int getTraitRatingCost(Trait trait, RatingCost ratingCosts) {
    int traitCosts = 0;
    int currentRating = trait.getCreationValue();
    while (currentRating < trait.getExperiencedValue()) {
      traitCosts += ratingCosts.getRatingCosts(currentRating);
      currentRating++;
    }
    return traitCosts;
  }
}