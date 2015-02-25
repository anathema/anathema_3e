package net.sf.anathema.hero.charms.compiler.special.learning;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.learning.SubEffect;
import net.sf.anathema.hero.charms.compiler.special.AdditionalCharmFactory;
import net.sf.anathema.hero.charms.compiler.special.CharmSpecialLearningBuilder;
import net.sf.anathema.hero.charms.compiler.special.ExistingMechanicTemplateSupplier;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.subeffects.SubEffectCharm;

@SuppressWarnings("UnusedDeclaration")
public class SubEffectCharmBuilder implements CharmSpecialLearningBuilder {

  @Override
  public CharmSpecialLearning readCharm(SpecialCharmTemplate overallDto,
  		AdditionalCharmFactory factory,
  		ExistingMechanicTemplateSupplier supplier) {
    SubEffect subEffect = overallDto.subEffect;
    String[] effects = subEffect.subEffects.toArray(new String[subEffect.subEffects.size()]);
    return new SubEffectCharm(new CharmName(overallDto.charmId), effects, subEffect.cost);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.subEffect != null;
  }
}