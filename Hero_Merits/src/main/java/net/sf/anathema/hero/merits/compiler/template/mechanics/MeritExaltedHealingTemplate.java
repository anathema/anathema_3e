package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.merits.model.mechanics.MeritExaltedHealingDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("AddsExaltedHealing")
public class MeritExaltedHealingTemplate extends MeritMechanicalDetailTemplate {

	@Override
	public MeritMechanicalDetail generate() {
		return new MeritExaltedHealingDetail();
	}
}
