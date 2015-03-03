package net.sf.anathema.hero.merits.model.requirements;

import net.sf.anathema.hero.individual.model.Hero;

import java.util.ArrayList;
import java.util.List;

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