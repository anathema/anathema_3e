package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.abilities.template.advance.AbilityPointsTemplate;
import net.sf.anathema.hero.traits.advance.CurrentRatingCost;
import net.sf.anathema.hero.traits.advance.FixedValueRatingCost;
import net.sf.anathema.hero.traits.advance.TraitListCreationData;
import net.sf.anathema.hero.traits.model.TraitType;

public class AbilityCreationData implements TraitListCreationData {

  private AbilityPointsTemplate template;


  public AbilityCreationData(AbilityPointsTemplate template) {
    this.template = template;
  }

  @Override
  public int getCalculationBase(TraitType type) {
    return template.standard.calculation.calculationBase;
  }

  public CurrentRatingCost getAbilityCosts(boolean casteOrFavored) {
    if (casteOrFavored) {
      return new FixedValueRatingCost(template.standard.favoredCost.bonusPoints);
    }
    return new FixedValueRatingCost(template.standard.defaultCost.bonusPoints);
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
