package net.sf.anathema.hero.merits.model.mechanics;

import net.sf.anathema.hero.health.model.HealthLevelType;

import java.util.List;

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
