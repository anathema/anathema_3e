package net.sf.anathema.hero.traits.model.rules.limitation;

import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.template.LimitationTemplate;

public interface LimitationFactory {

  boolean supports(LimitationTemplate template);

  TraitLimitation createLimitation(LimitationTemplate limitation);
}
