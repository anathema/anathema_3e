package net.sf.anathema.hero.attributes.template;

import net.sf.anathema.hero.traits.template.cost.CurrentRatingCostTemplate;
import net.sf.anathema.hero.traits.template.cost.TraitPointCalculationTemplate;

public class AttributePointsTemplate {

  public TraitPointCalculationTemplate standard = new TraitPointCalculationTemplate();
  public AttributeGroupPointsTemplate freebies = new AttributeGroupPointsTemplate();
  public AttributeGroupPointsTemplate bonusPoints = new AttributeGroupPointsTemplate();
  public CurrentRatingCostTemplate experiencePoints = new CurrentRatingCostTemplate();
}
