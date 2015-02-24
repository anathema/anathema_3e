package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;

public interface CharmSpecialLearningBuilder {

  CharmSpecialLearning readCharm(SpecialCharmTemplate dto, AdditionalCharmFactory factory);

  boolean supports(SpecialCharmTemplate dto);
}