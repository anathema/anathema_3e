package net.sf.anathema.hero.merits.model.requirements;

import net.sf.anathema.hero.individual.model.Hero;

public class MeritMortalRequirements implements MeritRequirement {

	@Override
	public boolean isSatisfied(Hero hero) {
		return !hero.getSplat().getTemplateType().getHeroType().isEssenceUser();
	}

}
