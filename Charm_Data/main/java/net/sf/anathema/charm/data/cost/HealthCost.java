package net.sf.anathema.charm.data.cost;

import net.sf.anathema.charm.data.cost.Cost;
import net.sf.anathema.charm.data.cost.HealthCostType;

public interface HealthCost extends Cost {

  HealthCostType getType();
}