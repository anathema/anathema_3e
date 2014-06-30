package net.sf.anathema.hero.magic.parser.dto;

import net.sf.anathema.charm.old.cost.HealthCostType;

public class HealthCostDto {

  public int cost = 0;
  public String text = "";
  public boolean permanent = false;
  public HealthCostType type = HealthCostType.Lethal;
}