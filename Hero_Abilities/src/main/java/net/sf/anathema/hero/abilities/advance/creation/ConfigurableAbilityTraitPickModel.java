package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.points.display.overview.model.AbstractSpendingModel;

public class ConfigurableAbilityTraitPickModel extends AbstractSpendingModel {

  private final AbilityCostCalculator abilityCalculator;
  private final int freebieCount;
  private final TraitStateType state;

  public ConfigurableAbilityTraitPickModel(AbilityCostCalculator abilityCalculator, int freebieCount,
                                           TraitStateType state) {
    super("Abilities", state.getId() + "Pick");
    this.abilityCalculator = abilityCalculator;
    this.freebieCount = freebieCount;
    this.state = state;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getPicksSpent(state);
  }

  @Override
  public int getAllotment() {
    return freebieCount;
  }
}