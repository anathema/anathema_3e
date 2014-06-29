package net.sf.anathema.hero.attributes.advance.experience;

import net.sf.anathema.hero.template.experience.CurrentRatingCost;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;

public class AttributesExperienceCalculator {

  private AttributesExperienceData experienceData;

  public AttributesExperienceCalculator(AttributesExperienceData experienceData) {
    this.experienceData = experienceData;
  }

  public int getAttributeCosts(Trait trait, boolean favored) {
    return getTraitRatingCosts(trait, experienceData.getAttributeCosts(favored));
  }

  private int getTraitRatingCosts(Trait trait, CurrentRatingCost ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCost(trait, ratingCosts);
  }
}
