package net.sf.anathema.hero.spiritual.advance.experience;

import net.sf.anathema.hero.spiritual.template.points.SpiritualPointsTemplate;
import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.advance.FixedValueRatingCost;

public class SpiritualExperienceData {

  private SpiritualPointsTemplate template;

  public SpiritualExperienceData(SpiritualPointsTemplate template) {
    this.template = template;
  }

  public CurrentRatingCost getEssenceCost() {
    return new FixedValueRatingCost(template.essence.cost.experiencePoints);
  }

  public CurrentRatingCost getWillpowerCost() {
    return new FixedValueRatingCost(template.willpower.cost.experiencePoints);
  }
}
