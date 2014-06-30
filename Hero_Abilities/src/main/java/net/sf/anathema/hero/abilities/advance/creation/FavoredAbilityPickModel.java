package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.points.display.overview.model.AbstractSpendingModel;

public class FavoredAbilityPickModel extends AbstractSpendingModel {

  private final AbilityCostCalculator abilityCalculator;
  private int favoredCount;

  public FavoredAbilityPickModel(AbilityCostCalculator abilityCalculator, int favoredCount) {
    super("Abilities", "FavoredPick");
    this.abilityCalculator = abilityCalculator;
    this.favoredCount = favoredCount;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getFavoredPicksSpent();
  }

  @Override
  public int getAllotment() {
    return favoredCount;
  }
}