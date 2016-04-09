package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.hero.traits.model.Trait;

public class TraitRatingCostCalculator {

	public static int getTraitRatingCost(Trait trait, RatingCost ratingCosts) {
		return getTraitRatingCost(ratingCosts, trait.getCreationValue(), trait.getExperiencedValue());
	}
	
  public static int getTraitRatingCost(RatingCost ratingCosts, int initialLevel, int targetLevel) {
    int traitCosts = 0;
    int currentRating = initialLevel;
    while (currentRating < targetLevel) {
      traitCosts += ratingCosts.getRatingCosts(currentRating);
      currentRating++;
    }
    return traitCosts;
  }
}