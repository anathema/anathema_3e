package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.magic.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ICharmSpecialLearningVisitor;

public class SubEffectCharm extends MultipleEffectCharm implements ISubEffectCharm {

  private final double pointCost;

  public SubEffectCharm(CharmName charmId, String[] subEffectIds, double pointCost) {
    super(charmId, subEffectIds);
    this.pointCost = pointCost;
  }

  @Override
  public double getPointCost() {
    return pointCost;
  }

  @Override
  public void accept(ICharmSpecialLearningVisitor visitor) {
    visitor.visitSubEffectCharm(this);
  }
}