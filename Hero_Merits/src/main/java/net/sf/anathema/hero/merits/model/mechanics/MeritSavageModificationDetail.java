package net.sf.anathema.hero.merits.model.mechanics;

import net.sf.anathema.equipment.stats.WeaponTag;

import java.util.List;
import java.util.Map;

public class MeritSavageModificationDetail extends MeritUnarmedModificationDetail {

	public MeritSavageModificationDetail(List<Integer> levels, Map<WeaponTag, WeaponTag> transformations) {
		super(levels, transformations);
	}
	
	@Override
	public void accept(MeritMechanicalDetailVisitor visitor) {
		visitor.visitSavageModificationDetail(this);
	}
}
