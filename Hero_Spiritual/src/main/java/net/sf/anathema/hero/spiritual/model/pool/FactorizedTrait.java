package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.traits.model.Trait;

public class FactorizedTrait {

  private final Trait[] traits;
  private final int factor;

  public FactorizedTrait(Trait[] traits, int factor) {
    this.traits = traits;
    this.factor = factor;
  }

  public FactorizedTrait(Trait trait, int factor) {
    this(new Trait[] { trait }, factor);
  }

  public int getCalculateTotal() {
    int sum = 0;
    for (Trait trait : traits) {
      sum += trait.getCurrentValue();
    }
    return sum * factor;
  }
}