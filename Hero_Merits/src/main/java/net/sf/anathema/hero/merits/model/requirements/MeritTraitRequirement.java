package net.sf.anathema.hero.merits.model.requirements;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.traits.TraitTypeFinder;
import net.sf.anathema.hero.traits.model.TraitModel;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

public class MeritTraitRequirement implements MeritRequirement {

	private final String trait;
	private final int value;
	
	public MeritTraitRequirement(String trait, int value) {
		this.trait = trait;
		this.value = value;
	}
	
	@Override
	public boolean isSatisfied(Hero hero) {
		TraitModel traitModel = TraitModelFetcher.fetch(hero);
		return traitModel.getTrait(new TraitTypeFinder().getTrait(trait)).getCurrentValue() >= value;
	}

	@Override
	public List<String> getContingentTraitTypes() {
		List<String> traitList = new ArrayList<>();
		traitList.add(trait);
		return traitList;
	}

}
