package net.sf.anathema.hero.traits.experience;

import static java.text.MessageFormat.format;

import net.sf.anathema.hero.traits.advance.RatingCost;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.library.text.SimpleTextualDescription;
import net.sf.anathema.points.model.xp.ExperiencePointCharge;

public abstract class TraitExperienceCharge implements ExperiencePointCharge {

	private Trait trait;
	private RatingCost ratingCost;
	private int initialLevel;
	private int targetLevel;
	
	protected TraitExperienceCharge(Trait trait, RatingCost ratingCost,
			int initialLevel, int targetLevel)
	{
		this.trait = trait;
		this.ratingCost = ratingCost;
		this.initialLevel = initialLevel;
		this.targetLevel = targetLevel;
	}
	
	@Override
	public int getExperiencePointCharge() {
		return TraitRatingCostCalculator.getTraitRatingCost(ratingCost, initialLevel, targetLevel);
	}

	@Override
	public ITextualDescription getTextualDescription(Resources resources) {
		return new SimpleTextualDescription(format(resources.getString("Traits.Experience.EntryDescriptionTemplate"),
				trait.getType().getId(),
				initialLevel,
				targetLevel));
	}
	
	@Override
	public void rollBack() {
		trait.setExperiencedValue(initialLevel);
	}

}
