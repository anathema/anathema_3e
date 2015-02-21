package net.sf.anathema.hero.health.model;

import net.sf.anathema.hero.individual.model.Hero;

public class DefaultHealingTypeProvider implements HealingTypeProvider {
	
	boolean isExaltedType;
	
	public DefaultHealingTypeProvider(Hero hero) {
		isExaltedType = hero.getSplat().getTemplateType().getHeroType().isExaltType();
	}

	@Override
	public boolean usesExaltedHealing() {
		return isExaltedType;
	}

}
