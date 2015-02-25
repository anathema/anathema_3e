package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;
import net.sf.anathema.hero.individual.persistence.values.ValueFactory;

public interface CharmSpecialMechanicsBuilder {
	CharmSpecialMechanic readCharm(SpecialCharmTemplate dto, String id, ValueFactory valueFactory);

  boolean supports(SpecialCharmTemplate dto);
}
