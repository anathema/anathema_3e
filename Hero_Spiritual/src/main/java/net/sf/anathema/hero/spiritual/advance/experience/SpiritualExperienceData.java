package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.hero.spiritual.template.points.CurrentRatingCostTemplate;
import net.sf.anathema.hero.spiritual.template.points.SpiritualPointsTemplate;
import net.sf.anathema.hero.template.experience.CurrentRatingCost;
import net.sf.anathema.hero.template.points.MultiplyRatingCost;

public class SpiritualExperienceData {

  private SpiritualPointsTemplate template;

  public SpiritualExperienceData(SpiritualPointsTemplate template) {
    this.template = template;
  }

  public CurrentRatingCost getEssenceCost() {
    return createCost(template.essence.experienceCost);
  }

  public CurrentRatingCost getWillpowerCost() {
    return createCost(template.willpower.experienceCost);
  }

  private CurrentRatingCost createCost(CurrentRatingCostTemplate costTemplate) {
    return new MultiplyRatingCost(costTemplate.factor, costTemplate.firstDotCost, costTemplate.addend);
  }
}
