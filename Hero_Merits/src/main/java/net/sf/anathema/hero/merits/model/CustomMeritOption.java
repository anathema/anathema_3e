package net.sf.anathema.hero.merits.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.individual.model.Hero;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.hero.traits.model.types.ITraitTypeVisitor;

public class CustomMeritOption implements MeritOption {

	private final String name;
	
	public CustomMeritOption(String name) {
		this.name = name;
	}
	
	@Override
	public void accept(ITraitTypeVisitor visitor) {
		// TODO: Needs visitor
	}

	@Override
	public String getId() {
		return name;
	}

	@Override
	public MeritCategory getType() {
		return MeritCategory.Purchased;
	}

	@Override
	public boolean allowsRepurchase() {
		return true;
	}

	@Override
	public boolean isHeroEligible(Hero hero) {
		return true;
	}

	@Override
	public boolean isLegalValue(int value) {
		return true;
	}

	@Override
	public List<String> getContingentTraitTypes() {
		return new ArrayList<>();
	}

	@Override
	public int getMinimumValue() {
		return 0;
	}

	@Override
	public int getMaximumValue() {
		return MAX_MERIT_RATING;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MeritOption) {
			return ((MeritOption)obj).getId().equals(getId());
		}
		return false;
	}

	@Override
	public List<MeritMechanicalDetail> getMechanics() {
		return new ArrayList<>();
	}

}
