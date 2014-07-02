package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.traits.model.SystemConstants;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;

public class EssenceFixedMultiLearnableCharm extends TraitDependentMultiLearnableCharm {

  public EssenceFixedMultiLearnableCharm(CharmName charmId) {
    super(charmId, SystemConstants.SYSTEM_ESSENCE_MAX, OtherTraitType.Essence);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return getMaximumLearnCount(learnRangeContext);
  }
}