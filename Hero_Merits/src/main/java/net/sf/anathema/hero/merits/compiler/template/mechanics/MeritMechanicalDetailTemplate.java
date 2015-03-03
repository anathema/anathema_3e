package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.platform.persistence.JsonField;

import java.util.List;

@JsonField("type")
public abstract class MeritMechanicalDetailTemplate {
	public List<Integer> levels;
	
	public abstract MeritMechanicalDetail generate();
}
