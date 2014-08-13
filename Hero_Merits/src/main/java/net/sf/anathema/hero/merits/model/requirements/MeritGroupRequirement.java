package net.sf.anathema.hero.merits.model.requirements;

import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;

public class MeritGroupRequirement implements MeritRequirement {
	
	private final List<MeritRequirement> requirements;
	
	public MeritGroupRequirement(List<MeritRequirement> requirements) {
		this.requirements = requirements;
	}

	@Override
	public boolean isSatisfied(Hero hero) {
		for (MeritRequirement requirement : requirements) {
			if (requirement.isSatisfied(hero)) {
				return true;
			}
		}
		return false;
	}

}
