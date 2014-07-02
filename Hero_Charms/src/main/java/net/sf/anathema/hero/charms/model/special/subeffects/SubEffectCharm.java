package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.charm.data.reference.CharmName;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;

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
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitSubEffectCharm(this);
  }
}