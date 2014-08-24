package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.special.MultiEffect;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharm;

@SuppressWarnings("UnusedDeclaration")
public class MultiEffectCharmBuilder implements SpecialCharmBuilder {

  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate overallDto, AdditionalCharmFactory factory) {
    MultiEffect multiEffect = overallDto.multiEffect;
    String[] effects = multiEffect.effects.toArray(new String[multiEffect.effects.size()]);
    return new MultipleEffectCharm(new CharmName(overallDto.charmId), effects);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.multiEffect != null;
  }
}