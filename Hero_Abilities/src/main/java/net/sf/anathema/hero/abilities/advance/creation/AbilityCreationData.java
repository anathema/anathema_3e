package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplate;
import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCosts;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;
import net.sf.anathema.hero.traits.model.TraitType;

public class AbilityCreationData implements TraitListCreationData {

  private AbilityPointsTemplate template;


  public AbilityCreationData(AbilityPointsTemplate template) {
    this.template = template;
  }

  @Override
  public int getCalculationBase(TraitType type) {
    return template.standard.calculationBase;
  }

  public CurrentRatingCosts getAbilityCosts(boolean casteOrFavored) {
    if (casteOrFavored) {
      return new FixedValueRatingCosts(template.bonusPointCosts.favoredCost);
    }
    return new FixedValueRatingCosts(template.bonusPointCosts.defaultCost);
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
