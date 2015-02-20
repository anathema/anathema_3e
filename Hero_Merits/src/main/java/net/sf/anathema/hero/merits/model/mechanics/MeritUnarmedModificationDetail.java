package net.sf.anathema.hero.merits.model.mechanics;

import java.util.List;
import java.util.Map;

import net.sf.anathema.equipment.stats.WeaponTag;

public class MeritUnarmedModificationDetail implements MeritMechanicalDetail {

	private Map<WeaponTag, WeaponTag> transformations;
	private List<Integer> levels;
	
	public MeritUnarmedModificationDetail(List<Integer> levels, Map<WeaponTag, WeaponTag> transformations) {
		this.transformations = transformations;
		this.levels = levels;
	}
	
	@Override
	public void accept(MeritMechanicalDetailVisitor visitor) {
		visitor.visitUnarmedModificationDetail(this);
	}
	
	public boolean hasTransformationForTag(int level, WeaponTag tag) {
		if (!appliesToLevel(level))
		{
			return false;
		}
		return transformations.containsKey(tag);
	}
	
	public WeaponTag getTransformationForTag(int level, WeaponTag tag) {
		if (!appliesToLevel(level))
		{
			return tag;
		}
		return transformations.get(tag);
	}
	
	private boolean appliesToLevel(int level) {
		return levels == null || !levels.contains(level);
	}

}
