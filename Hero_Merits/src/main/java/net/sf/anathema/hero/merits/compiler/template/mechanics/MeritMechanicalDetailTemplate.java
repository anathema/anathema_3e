package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public abstract class MeritMechanicalDetailTemplate {
	
	public abstract MechanicalDetail generate();
}
