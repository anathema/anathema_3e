package net.sf.anathema.hero.magic.basic.cost;

public interface ICostList {

  Cost getEssenceCost();

  IHealthCost getHealthCost();

  Cost getWillpowerCost();

  Cost getXPCost();
}