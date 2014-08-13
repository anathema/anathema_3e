package net.sf.anathema.hero.merits.advance;

import net.sf.anathema.hero.merits.template.MeritPointsTemplate;
import net.sf.anathema.hero.traits.advance.NewRatingCost;
import net.sf.anathema.hero.traits.template.cost.NewRatingCostTemplate;

public class MeritExperienceData {
	private final MeritPointsTemplate template;
	
	public MeritExperienceData(MeritPointsTemplate template) {
		this.template = template;
	}
	
	public NewRatingCost getCostCalculator() {
		return NewRatingCostTemplate.createCost(template.experiencePoints);
	}
}
