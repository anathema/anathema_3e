package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

public interface SpecialCharmBuilder {

  ISpecialCharm readCharm(SpecialCharmTemplate dto);

  boolean supports(SpecialCharmTemplate dto);
}