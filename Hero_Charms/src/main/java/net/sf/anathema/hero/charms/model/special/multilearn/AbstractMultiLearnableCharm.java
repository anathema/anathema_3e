package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;

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
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultiLearnableCharm(this);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return 1;
  }
}