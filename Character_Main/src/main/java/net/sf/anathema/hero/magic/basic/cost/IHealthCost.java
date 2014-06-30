package net.sf.anathema.hero.magic.basic.cost;

import net.sf.anathema.hero.health.HealthType;

public interface IHealthCost extends Cost {
  HealthType getType();
}