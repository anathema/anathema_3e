package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.parser.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.parser.template.special.SubEffect;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharm;

@SuppressWarnings("UnusedDeclaration")
public class SubEffectCharmBuilder implements SpecialCharmBuilder {

  @Override
  public ISpecialCharm readCharm(SpecialCharmTemplate overallDto) {
    SubEffect subEffect = overallDto.subEffect;
    String[] effects = subEffect.subEffects.toArray(new String[subEffect.subEffects.size()]);
    return new SubEffectCharm(new CharmName(overallDto.charmId), effects, subEffect.cost);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.subEffect != null;
  }
}