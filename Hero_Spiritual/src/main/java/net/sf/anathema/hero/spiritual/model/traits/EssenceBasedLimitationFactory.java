package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.traits.model.rules.limitation.*;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;

public class EssenceBasedLimitationFactory implements LimitationFactory {
  @Override
  public boolean supports(LimitationTemplate template) {
    return template.type == LimitationType.Essence;
  }

  @Override
  public TraitLimitation createLimitation(LimitationTemplate limitation) {
    return new EssenceBasedLimitation();
  }
}
