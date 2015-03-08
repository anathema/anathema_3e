package net.sf.anathema.hero.charms.compiler.special.mechanics;

import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.magic.template.special.SpecialCharmTemplate;
import net.sf.anathema.magic.template.special.mechanics.ThaumaturgyTemplate;
import net.sf.anathema.hero.charms.compiler.special.CharmSpecialMechanicsBuilder;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.charms.model.special.mechanics.ThaumaturgyMechanic;
import net.sf.anathema.hero.individual.persistence.values.ValueFactory;

public class ThaumaturgyCharmBuilder implements CharmSpecialMechanicsBuilder{
	
	@Override
	public CharmSpecialMechanic readCharm(SpecialCharmTemplate dto, String id, ValueFactory valueFactory) {
		ThaumaturgyTemplate template = dto.thaumaturgy;
		return new ThaumaturgyMechanic(new CharmName(id),
				template.grantsAccess,
				valueFactory.getValueForTemplate(template.grantsFreeRituals));
	}

	@Override
	public boolean supports(SpecialCharmTemplate dto) {
		return dto.thaumaturgy != null;
	}

}
