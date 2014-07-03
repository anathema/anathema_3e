package net.sf.anathema.charm.old.cost;

import com.google.common.base.Preconditions;

public class HealthCostImpl extends CostImpl implements HealthCost {

  public static final HealthCost NULL_HEALTH_COST = new HealthCostImpl(0, "", false, HealthCostType.Lethal);
  private final HealthCostType type;

  public HealthCostImpl(int cost, String text, boolean permanent, HealthCostType type) {
    super(String.valueOf(cost), text, permanent);
    Preconditions.checkNotNull(type);
    this.type = type;
  }

  @Override
  public HealthCostType getType() {
    return type;
  }
}