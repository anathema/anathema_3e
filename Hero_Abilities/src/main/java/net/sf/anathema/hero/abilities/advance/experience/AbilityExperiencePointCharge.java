package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.traits.advance.RatingCost;
import net.sf.anathema.hero.traits.experience.TraitExperienceCharge;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.points.model.xp.ExperienceExpenditureType;

public class AbilityExperiencePointCharge extends TraitExperienceCharge {

	public AbilityExperiencePointCharge(Trait trait, RatingCost ratingCost, int initialLevel,
			int targetLevel) {
		super(trait, ratingCost, initialLevel, targetLevel);
	}

	@Override
	public ExperienceExpenditureType getType() {
		return AbilityExperienceExpenditureType.Type;
	}
}
