package net.sf.anathema.hero.health.template.merit;

import net.sf.anathema.hero.merits.compiler.template.mechanics.MeritMechanicalDetailTemplate;
import net.sf.anathema.hero.merits.model.mechanics.GenericMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("AddsExaltedHealing")
public class MeritExaltedHealingTemplate extends MeritMechanicalDetailTemplate {

	@Override
	public MechanicalDetail generate() {
		return new GenericMechanicalDetail("AddsExaltedHealing");
	}
}
