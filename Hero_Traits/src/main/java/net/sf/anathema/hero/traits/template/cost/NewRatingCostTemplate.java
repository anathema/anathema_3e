package net.sf.anathema.hero.traits.template.cost;

import net.sf.anathema.hero.traits.advance.MultiplyRatingCost;
import net.sf.anathema.hero.traits.advance.NewRatingCost;

public class NewRatingCostTemplate {

  public int factor = 0;

  public static NewRatingCost createCost(NewRatingCostTemplate costTemplate) {
    return new MultiplyRatingCost(costTemplate.factor, costTemplate.factor, costTemplate.factor);
  }
}
