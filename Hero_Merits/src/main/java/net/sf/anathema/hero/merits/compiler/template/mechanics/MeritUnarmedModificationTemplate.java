package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.equipment.stats.WeaponTag;
import net.sf.anathema.hero.merits.model.mechanics.MeritMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MeritUnarmedModificationDetail;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.Map;

@JsonType("AddsUnarmedModification")
public class MeritUnarmedModificationTemplate extends MeritMechanicalDetailTemplate {
	public Map<WeaponTag, WeaponTag> transformations;

	@Override
	public MeritMechanicalDetail generate() {
		return new MeritUnarmedModificationDetail(levels, transformations);
	}
}
