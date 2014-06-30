package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

public class DefaultAbilityBonusModel extends AbstractSpendingModel {
  private final AbilityCostCalculator abilityCalculator;
  private AbilityCreationData creationData;

  public DefaultAbilityBonusModel(AbilityCostCalculator abilityCalculator, AbilityCreationData creationData) {
    super("Abilities", "General");
    this.abilityCalculator = abilityCalculator;
    this.creationData = creationData;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getFreePointsSpent(false);
  }

  @Override
  public int getSpentBonusPoints() {
    return abilityCalculator.getBonusPointCost();
  }

  @Override
  public int getAllotment() {
    return creationData.getGeneralDotCount();
  }
}