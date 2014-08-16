package net.sf.anathema.hero.merits.model.requirements;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;

public class MeritMortalRequirements implements MeritRequirement {

	@Override
	public boolean isSatisfied(Hero hero) {
		return !hero.getSplat().getTemplateType().getHeroType().isEssenceUser();
	}

	@Override
	public List<String> getContingentTraitTypes() {
		return new ArrayList<>();
	}
}