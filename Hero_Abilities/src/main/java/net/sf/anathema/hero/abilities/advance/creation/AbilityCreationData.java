package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.abilities.template.AbilityPointsTemplate;
import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;
import net.sf.anathema.hero.traits.model.TraitType;

public class AbilityCreationData implements TraitListCreationData {

  private AbilityPointsTemplate template;
  private BonusPointCosts costs;


  public AbilityCreationData(AbilityPointsTemplate template, BonusPointCosts costs) {
    this.template = template;
    this.costs = costs;
  }

  @Override
  public int getCalculationBase(TraitType type) {
    return template.standard.calculationBase;
  }

  public CurrentRatingCosts getAbilityCosts(boolean casteOrFavored) {
    return costs.getAbilityCosts(casteOrFavored);
  }

  public int getMaximumFreeAbilityRank() {
    return template.maximalFreebieRank;
  }

  public int getGeneralDotCount() {
    return template.generalDots;
  }

  public int getFavoredDotCount() {
    return template.favoredDots;
  }
}
