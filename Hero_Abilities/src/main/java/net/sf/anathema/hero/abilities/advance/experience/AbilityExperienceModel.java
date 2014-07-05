package net.sf.anathema.hero.abilities.advance.experience;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.state.TraitStateMap;
import net.sf.anathema.points.display.overview.model.AbstractIntegerValueModel;

public class AbilityExperienceModel extends AbstractIntegerValueModel {

  private final AbilitiesModel abilities;
  private final AbilityExperienceCalculator calculator;

  public AbilityExperienceModel(AbilitiesModel abilities, AbilityExperienceCalculator calculator) {
    super("Experience", "Abilities");
    this.abilities = abilities;
    this.calculator = calculator;
  }

  @Override
  public Integer getValue() {
    return getAbilityCosts();
  }

  private int getAbilityCosts() {
    int experienceCosts = 0;
    for (Trait ability : abilities.getAll()) {
      TraitStateMap stateMap = abilities.getStateMap();
      experienceCosts += calculator.getAbilityCosts(ability, stateMap.isCasteOrFavored(ability));
    }
    return experienceCosts;
  }
}