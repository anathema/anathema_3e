package net.sf.anathema.hero.traits.experience;

import net.sf.anathema.hero.traits.advance.RatingCost;
import net.sf.anathema.hero.traits.advance.TraitRatingCostCalculator;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.library.text.ITextualDescription;
import net.sf.anathema.library.text.SimpleTextualDescription;
import net.sf.anathema.points.model.xp.ExperiencePointEntry;

import static java.text.MessageFormat.format;

public abstract class TraitExperienceEntry implements ExperiencePointEntry {

	private Trait trait;
	private RatingCost ratingCost;
	private int initialLevel;
	private int targetLevel;
	
	private ITextualDescription textualDescription;
	
	protected TraitExperienceEntry(Trait trait, RatingCost ratingCost,
			int initialLevel, int targetLevel)
	{
		this.trait = trait;
		this.ratingCost = ratingCost;
		this.initialLevel = initialLevel;
		this.targetLevel = targetLevel;
	}
	
	@Override
	public int getExperiencePoints() {
		return -TraitRatingCostCalculator.getTraitRatingCost(ratingCost, initialLevel, targetLevel);
	}

	@Override
	public void setExperiencePoints(int points) {
		throw new UnsupportedOperationException();
	}
	
	public void initializePresentation(Resources resources) {
		if (textualDescription == null)
		{
			textualDescription = new SimpleTextualDescription(format(resources.getString("Traits.Experience.EntryDescriptionTemplate"),
					trait.getType().getId(),
					initialLevel,
					targetLevel));
		}
	}

	@Override
	public ITextualDescription getTextualDescription() {
		return textualDescription;
	}
	
	@Override
	public boolean allowModification() {
		return false;
	}
	
	@Override
	public void rollBack() {
		trait.setExperiencedValue(initialLevel);
	}

}
