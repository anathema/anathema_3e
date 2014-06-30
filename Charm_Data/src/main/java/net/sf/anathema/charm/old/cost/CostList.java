package net.sf.anathema.charm.old.cost;

public interface CostList {

  Cost getEssenceCost();

  HealthCost getHealthCost();

  Cost getWillpowerCost();

  Cost getXPCost();
}