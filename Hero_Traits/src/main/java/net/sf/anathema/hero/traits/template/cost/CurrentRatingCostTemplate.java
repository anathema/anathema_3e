package net.sf.anathema.hero.traits.template.cost;

import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.advance.MultiplyRatingCost;

public class CurrentRatingCostTemplate {

  public int factor = 0;
  public int addend = 0;
  public int firstDotCost = 0;

  public static CurrentRatingCost createCost(CurrentRatingCostTemplate costTemplate) {
    return new MultiplyRatingCost(costTemplate.factor, costTemplate.firstDotCost, costTemplate.addend);
  }
}
