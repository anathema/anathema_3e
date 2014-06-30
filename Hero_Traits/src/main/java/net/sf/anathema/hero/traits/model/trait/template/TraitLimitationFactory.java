package net.sf.anathema.hero.traits.model.trait.template;

import net.sf.anathema.hero.traits.model.limitation.TraitLimitation;
import net.sf.anathema.hero.traits.template.LimitationTemplate;

public interface TraitLimitationFactory {

  boolean supports(LimitationTemplate template);

  TraitLimitation createLimitation(LimitationTemplate limitation);
}
