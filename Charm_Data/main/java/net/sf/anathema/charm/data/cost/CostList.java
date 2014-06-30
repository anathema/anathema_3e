package net.sf.anathema.charm.data.cost;

public interface CostList {

  Cost getEssenceCost();

  HealthCost getHealthCost();

  Cost getWillpowerCost();

  Cost getXPCost();
}