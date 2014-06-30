package net.sf.anathema.charm.old.cost;

public class CostListImpl implements CostList {

  private final Cost essence;
  private final Cost willpower;
  private final HealthCost health;
  private final Cost xp;

  public CostListImpl(Cost essence, Cost willpower, HealthCost health, Cost xp) {
    this.xp = xp;
    this.essence = essence;
    this.willpower = willpower;
    this.health = health;
  }

  @Override
  public Cost getXPCost() {
    return xp != null ? xp : CostImpl.NULL_COST;
  }

  @Override
  public Cost getEssenceCost() {
    return essence != null ? essence : CostImpl.NULL_COST;
  }

  @Override
  public HealthCost getHealthCost() {
    return health != null ? health : HealthCostImpl.NULL_HEALTH_COST;
  }

  @Override
  public Cost getWillpowerCost() {
    return willpower != null ? willpower : CostImpl.NULL_COST;
  }
}