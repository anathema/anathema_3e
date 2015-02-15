package net.sf.anathema.hero.merits.advance.calculator;

import net.sf.anathema.hero.merits.advance.MeritExperienceData;
import net.sf.anathema.hero.merits.model.Merit;
import net.sf.anathema.hero.traits.advance.NewRatingCost;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.traits.model.Trait;

public class MeritExperienceCalculator {
	private final MeritExperienceData experience;
	
	public MeritExperienceCalculator(MeritExperienceData experience) {
		this.experience = experience;
	}
	
	public int getMeritCosts(Merit merit) {
    return getTraitRatingCosts(merit, experience.getCostCalculator());
  }

  private int getTraitRatingCosts(Trait trait, NewRatingCost ratingCosts) {
    return TraitRatingCostCalculator.getTraitRatingCost(trait, ratingCosts);
  }
}