package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritPainToleranceDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("AddsPainTolerance")
public class MeritPainToleranceTemplate implements MeritMechanicalDetailTemplate {
	public int tolerance;

	@Override
	public MeritMechanicalDetail generate() {
		return new MeritPainToleranceDetail(tolerance);
	}
}
