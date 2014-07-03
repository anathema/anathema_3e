package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplate;
import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.template.cost.CurrentRatingCostTemplate;

public class AbilityExperienceData {

  private AbilityPointsTemplate template;

  public AbilityExperienceData(AbilityPointsTemplate template) {
    this.template = template;
  }

  public CurrentRatingCost getAbilityCosts(boolean favored) {
    return CurrentRatingCostTemplate.createCost(getCostTemplate(favored));
  }

  private CurrentRatingCostTemplate getCostTemplate(boolean favored) {
    return favored ? template.standard.favoredCost.experiencePoints : template.standard.defaultCost.experiencePoints;
  }
}
