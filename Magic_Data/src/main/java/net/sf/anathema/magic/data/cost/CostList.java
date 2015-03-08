package net.sf.anathema.magic.data.cost;

public interface CostList {

  Cost getEssenceCost();
  
  Cost getSorcerousMotesCost();

  HealthCost getHealthCost();

  Cost getWillpowerCost();

  Cost getXPCost();
}