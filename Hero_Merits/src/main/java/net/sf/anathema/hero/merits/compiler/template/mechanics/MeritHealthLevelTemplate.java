package net.sf.anathema.hero.merits.compiler.template.mechanics;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.merits.model.mechanics.MeritHealthDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("AddsHealthLevels")
public class MeritHealthLevelTemplate extends MeritMechanicalDetailTemplate {
	public List<HealthLevelType> healthLevels = new ArrayList<>();

	@Override
	public MeritMechanicalDetail generate() {
		return new MeritHealthDetail(healthLevels);
	}
}
