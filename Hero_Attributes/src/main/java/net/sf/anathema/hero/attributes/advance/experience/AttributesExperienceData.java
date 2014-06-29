package net.sf.anathema.hero.attributes.advance.experience;

import net.sf.anathema.hero.attributes.template.AttributePointsTemplate;
import net.sf.anathema.hero.template.experience.CurrentRatingCost;
import net.sf.anathema.hero.traits.template.cost.CurrentRatingCostTemplate;

public class AttributesExperienceData {

  private AttributePointsTemplate template;

  public AttributesExperienceData(AttributePointsTemplate template) {
    this.template = template;
  }

  public CurrentRatingCost getAttributeCosts(boolean favored) {
    return CurrentRatingCostTemplate.createCost(template.experiencePoints);
  }
}
