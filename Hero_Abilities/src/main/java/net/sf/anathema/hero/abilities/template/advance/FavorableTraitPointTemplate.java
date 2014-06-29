package net.sf.anathema.hero.abilities.template.advance;

import net.sf.anathema.hero.traits.template.cost.TraitCostTemplate;
import net.sf.anathema.hero.traits.template.cost.TraitPointCalculationTemplate;

public class FavorableTraitPointTemplate {

  public TraitPointCalculationTemplate calculation = new TraitPointCalculationTemplate();
  public TraitCostTemplate favoredCost = new TraitCostTemplate();
  public TraitCostTemplate defaultCost = new TraitCostTemplate();
}
