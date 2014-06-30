package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

public class FavoredAbilityBonusModel extends AbstractSpendingModel {
  private final AbilityCostCalculator abilityCalculator;
  private AbilityCreationData creationData;

  public FavoredAbilityBonusModel(AbilityCostCalculator abilityCalculator, AbilityCreationData creationData) {
    super("Abilities", "FavoredDot");
    this.abilityCalculator = abilityCalculator;
    this.creationData = creationData;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(true);
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public int getAllotment() {
    return creationData.getFavoredDotCount();
  }
}