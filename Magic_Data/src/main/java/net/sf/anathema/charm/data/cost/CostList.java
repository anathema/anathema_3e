package net.sf.anathema.charm.data.cost;

public interface CostList {

  Cost getEssenceCost();
  
  Cost getSorcerousMotesCost();

  HealthCost getHealthCost();

  Cost getWillpowerCost();

  Cost getXPCost();
}