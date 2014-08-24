package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public interface MeritMechanicalDetailTemplate {
	public MeritMechanicalDetail generate();
}
