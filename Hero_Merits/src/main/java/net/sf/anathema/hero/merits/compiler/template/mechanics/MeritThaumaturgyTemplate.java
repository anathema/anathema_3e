package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritThaumaturgyDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("GrantsThaumaturgy")
public class MeritThaumaturgyTemplate extends MeritMechanicalDetailTemplate {

	@Override
	public MeritMechanicalDetail generate() {
		return new MeritThaumaturgyDetail();
	}
}
