package net.sf.anathema.hero.charms.compiler.special.learning;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.charm.template.special.SpecialCharmTemplate;
import net.sf.anathema.charm.template.special.learning.MultiEffect;
import net.sf.anathema.hero.charms.compiler.special.AdditionalCharmFactory;
import net.sf.anathema.hero.charms.compiler.special.CharmSpecialLearningBuilder;
import net.sf.anathema.hero.charms.compiler.special.ExistingMechanicTemplateSupplier;
import net.sf.anathema.hero.charms.model.special.CharmSpecialLearning;
import net.sf.anathema.hero.charms.model.special.subeffects.MultipleEffectCharm;

@SuppressWarnings("UnusedDeclaration")
public class MultiEffectCharmBuilder implements CharmSpecialLearningBuilder {

  @Override
  public CharmSpecialLearning readCharm(SpecialCharmTemplate overallDto,
  		AdditionalCharmFactory factory,
  		ExistingMechanicTemplateSupplier supplier) {
    MultiEffect multiEffect = overallDto.multiEffect;
    String[] effects = multiEffect.effects.toArray(new String[multiEffect.effects.size()]);
    return new MultipleEffectCharm(new CharmName(overallDto.charmId), effects);
  }

  @Override
  public boolean supports(SpecialCharmTemplate overallDto) {
    return overallDto.multiEffect != null;
  }
}