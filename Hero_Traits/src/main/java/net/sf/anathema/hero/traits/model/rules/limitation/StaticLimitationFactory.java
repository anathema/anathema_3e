package net.sf.anathema.hero.traits.model.rules.limitation;

import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;

public class StaticLimitationFactory implements LimitationFactory {
  @Override
  public boolean supports(LimitationTemplate template) {
    return template.type == LimitationType.Static;
  }

  @Override
  public TraitLimitation createLimitation(LimitationTemplate limitation) {
    return new StaticTraitLimitation(limitation.value);
  }
}
