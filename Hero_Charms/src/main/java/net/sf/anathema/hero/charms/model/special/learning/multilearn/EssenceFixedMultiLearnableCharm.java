package net.sf.anathema.hero.charms.model.special.learning.multilearn;

import net.sf.anathema.hero.traits.model.SystemConstants;
import net.sf.anathema.magic.data.reference.CharmName;

import static net.sf.anathema.hero.traits.model.types.CommonTraitTypes.Essence;

public class EssenceFixedMultiLearnableCharm extends TraitDependentMultiLearnableCharm {

  public EssenceFixedMultiLearnableCharm(CharmName charmId) {
    super(charmId, SystemConstants.SYSTEM_ESSENCE_MAX, Essence);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return getMaximumLearnCount(learnRangeContext);
  }
}