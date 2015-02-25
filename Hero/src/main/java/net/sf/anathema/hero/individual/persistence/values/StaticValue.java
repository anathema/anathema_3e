package net.sf.anathema.hero.individual.persistence.values;

import net.sf.anathema.hero.individual.model.Hero;

public class StaticValue implements Value {
	private final int value;
	
	public StaticValue(int value) {
		this.value = value;
	}

	@Override
	public int getValueForHero(Hero hero) {
		return value;
	}
}
