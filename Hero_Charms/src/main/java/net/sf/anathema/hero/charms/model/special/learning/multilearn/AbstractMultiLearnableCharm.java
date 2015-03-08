package net.sf.anathema.hero.charms.model.special.learning.multilearn;

import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ICharmSpecialLearningVisitor;

public abstract class AbstractMultiLearnableCharm implements IMultiLearnableCharm {

  private final CharmName charmId;

  public AbstractMultiLearnableCharm(CharmName charmId) {
    this.charmId = charmId;
  }

  @Override
  public final CharmName getCharmName() {
    return charmId;
  }

  @Override
  public void accept(ICharmSpecialLearningVisitor visitor) {
    visitor.visitMultiLearnableCharm(this);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return 1;
  }
}