package net.sf.anathema.hero.merits.compiler.template.mechanics;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.hero.merits.model.mechanics.MeritExaltedHealingDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritHealthDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("AddsExaltedHealing")
public class MeritExaltedHealingTemplate extends MeritMechanicalDetailTemplate {

	@Override
	public MeritMechanicalDetail generate() {
		return new MeritExaltedHealingDetail();
	}
}
