package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.hero.spiritual.template.points.SpiritualPointsTemplate;
import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.template.cost.CurrentRatingCostTemplate;

public class SpiritualExperienceData {

  private SpiritualPointsTemplate template;

  public SpiritualExperienceData(SpiritualPointsTemplate template) {
    this.template = template;
  }

  public CurrentRatingCost getEssenceCost() {
    CurrentRatingCostTemplate costTemplate = template.essence.cost.experiencePoints;
    return CurrentRatingCostTemplate.createCost(costTemplate);
  }

  public CurrentRatingCost getWillpowerCost() {
    CurrentRatingCostTemplate costTemplate = template.willpower.cost.experiencePoints;
    return CurrentRatingCostTemplate.createCost(costTemplate);
  }
}
