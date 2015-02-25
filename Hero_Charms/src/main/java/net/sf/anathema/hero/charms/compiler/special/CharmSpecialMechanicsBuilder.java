package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.CharmSpecialMechanic;

public interface CharmSpecialMechanicsBuilder {
	CharmSpecialMechanic readCharm(SpecialCharmTemplate dto, String id);

  boolean supports(SpecialCharmTemplate dto);
}
