package net.sf.anathema.hero.merits.compiler.template.mechanics;

import net.sf.anathema.equipment.stats.WeaponTag;
import net.sf.anathema.hero.merits.model.mechanics.DetailEntryReference;
import net.sf.anathema.hero.merits.model.mechanics.GenericMechanicalDetail;
import net.sf.anathema.hero.merits.model.mechanics.MechanicalDetail;
import net.sf.anathema.platform.persistence.JsonType;

import java.util.Map;

@JsonType("AddsUnarmedModification")
public class MeritUnarmedModificationTemplate extends MeritMechanicalDetailTemplate {
	public Map<WeaponTag, WeaponTag> transformations;

	@Override
	public MechanicalDetail generate() {
      GenericMechanicalDetail detail = new GenericMechanicalDetail("AddsUnarmedModification");
      detail.addDetailEntry(new DetailEntryReference("levels"), levels);
      detail.addDetailEntry(new DetailEntryReference("transformations"), transformations);
      return detail;
    }
}