package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.hero.template.points.CurrentRatingCost;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.traits.model.Trait;

public class SpiritualExperienceCalculator {

  private SpiritualExperienceData experienceData;

  public SpiritualExperienceCalculator(SpiritualExperienceData experienceData) {
    this.experienceData = experienceData;
  }

  public int getEssenceCosts(Trait essence) {
    CurrentRatingCost cost = experienceData.getEssenceCost();
    return getTraitRatingCosts(essence, cost);
  }

  public int getWillpowerCosts(Trait willpower) {
    CurrentRatingCost cost = experienceData.getWillpowerCost();
    return getTraitRatingCosts(willpower, cost);
  }

  private int getTraitRatingCosts(Trait trait, CurrentRatingCost ratingCost) {
    return TraitRatingCostCalculator.getTraitRatingCost(trait, ratingCost);
  }
}