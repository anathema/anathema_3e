package net.sf.anathema.hero.traits.persistence.values;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.individual.persistence.values.Value;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;

public class TraitValue implements Value {
	private final TraitType trait;
	
	public TraitValue(TraitType trait) {
		this.trait = trait;
	}

	@Override
	public int getValueForHero(Hero hero) {
		return TraitModelFetcher.fetch(hero).getTrait(trait).getCurrentValue();
	}
}
