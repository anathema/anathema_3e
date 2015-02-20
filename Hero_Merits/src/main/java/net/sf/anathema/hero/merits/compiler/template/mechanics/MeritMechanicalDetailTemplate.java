package net.sf.anathema.hero.merits.compiler.template.mechanics;

import java.util.List;

import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.platform.persistence.JsonField;

@JsonField("type")
public abstract class MeritMechanicalDetailTemplate {
	public List<Integer> levels;
	
	public abstract MeritMechanicalDetail generate();
}
