package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

import static net.sf.anathema.hero.traits.model.state.SupernalTraitStateType.Supernal;

public class SupernalAbilityPickModel extends AbstractSpendingModel {

  private final AbilityCostCalculator abilityCalculator;
  private int supernalCount;

  public SupernalAbilityPickModel(AbilityCostCalculator abilityCalculator, int supernalCount) {
    super("Abilities", "SupernalPick");
    this.abilityCalculator = abilityCalculator;
    this.supernalCount = supernalCount;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getPicksSpent(Supernal);
  }

  @Override
  public int getAllotment() {
    return supernalCount;
  }
}