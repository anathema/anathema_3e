package net.sf.anathema.hero.merits.model.mechanics;

import java.util.List;

import net.sf.anathema.hero.health.model.HealthLevelType;

public class MeritHealthDetail implements MeritMechanicalDetail {

	private final List<HealthLevelType> healthLevels;
	
	public MeritHealthDetail(List<HealthLevelType> healthLevels) {
		this.healthLevels = healthLevels;
	}

	@Override
	public void accept(MeritMechanicalDetailVisitor visitor) {
		visitor.visitHealthDetail(this);
	}

	public List<HealthLevelType> getHealthLevels() {
		return healthLevels;
	}
}
