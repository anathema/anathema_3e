package net.sf.anathema.hero.merits.compiler.template.mechanics;

import java.util.Map;

import net.sf.anathema.equipment.stats.WeaponTag;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritSavageModificationDetail;
import net.sf.anathema.platform.persistence.JsonType;

@JsonType("AddsSavageModification")
public class MeritSavageModificationTemplate extends MeritMechanicalDetailTemplate {
	public Map<WeaponTag, WeaponTag> transformations;

	@Override
	public MeritMechanicalDetail generate() {
		return new MeritSavageModificationDetail(levels, transformations);
	}
}
