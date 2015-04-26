package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.abilities.advance.PointCalculationTraitHolder;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

public class AbilityExperienceModel extends AbstractIntegerValueModel {

  private final PointCalculationTraitHolder traits;
  private final AbilityExperienceCalculator calculator;

  public AbilityExperienceModel(PointCalculationTraitHolder traits, AbilityExperienceCalculator calculator) {
    super("Experience", "Abilities");
    this.traits = traits;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getAbilityCosts();
  }

  private int getAbilityCosts() {
    int experienceCosts = 0;
    for (Trait ability : traits.getAll()) {
      experienceCosts += calculator.getAbilityCosts(ability, traits.getState(ability).isCheapened());
    }
    return experienceCosts;
  }
}