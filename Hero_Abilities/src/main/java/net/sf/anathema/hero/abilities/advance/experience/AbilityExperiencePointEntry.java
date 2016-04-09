package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.traits.advance.RatingCost;
import net.sf.anathema.hero.traits.experience.TraitExperienceEntry;
import net.sf.anathema.hero.traits.model.Trait;

public class AbilityExperiencePointEntry extends TraitExperienceEntry {

	public AbilityExperiencePointEntry(Trait trait, RatingCost ratingCost, int initialLevel,
			int targetLevel) {
		super(trait, ratingCost, initialLevel, targetLevel);
	}


}
