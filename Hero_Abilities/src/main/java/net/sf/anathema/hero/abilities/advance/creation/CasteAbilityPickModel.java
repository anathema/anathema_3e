package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

import static net.sf.anathema.hero.traits.model.state.CasteTraitStateType.Caste;

public class CasteAbilityPickModel extends AbstractSpendingModel {

  private final AbilityCostCalculator abilityCalculator;
  private int casteCount;

  public CasteAbilityPickModel(AbilityCostCalculator abilityCalculator, int casteCount) {
    super("Abilities", "CastePick");
    this.abilityCalculator = abilityCalculator;
    this.casteCount = casteCount;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getPicksSpent(Caste);
  }

  @Override
  public int getAllotment() {
    return casteCount;
  }
}