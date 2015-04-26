package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.traits.model.TraitLimitation;
import net.sf.anathema.hero.traits.model.rules.limitation.LimitationFactory;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;

@SuppressWarnings("unused") //created through object factory
public class ExperienceLimitationFactory implements LimitationFactory {
  @Override
  public boolean supports(LimitationTemplate template) {
    return template.type == LimitationType.Experience;
  }

  @Override
  public TraitLimitation createLimitation(LimitationTemplate limitation) {
    return new ExperienceBasedLimitation();
  }
}
