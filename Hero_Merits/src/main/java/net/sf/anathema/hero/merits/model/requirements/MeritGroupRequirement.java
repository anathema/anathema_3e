package net.sf.anathema.hero.merits.model.requirements;

import net.sf.anathema.hero.individual.model.Hero;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<String> getContingentTraitTypes() {
		List<String> traits = new ArrayList<>();
		for (MeritRequirement requirement : requirements) {
			traits.addAll(requirement.getContingentTraitTypes());
		}
		return traits;
	}

}
