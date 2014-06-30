package net.sf.anathema.hero.traits.model.rules.limitation;

import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;

public class AgeBasedLimitationFactory implements LimitationFactory {
  @Override
  public boolean supports(LimitationTemplate template) {
    return template.type == LimitationType.Age;
  }

  @Override
  public TraitLimitation createLimitation(LimitationTemplate limitation) {
    return new AgeBasedLimitation(limitation.value);
  }
}
