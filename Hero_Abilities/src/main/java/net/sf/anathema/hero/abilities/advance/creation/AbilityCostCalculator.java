package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.traits.model.state.TraitStateType;
import net.sf.anathema.points.model.BonusPointCalculator;

public interface AbilityCostCalculator extends BonusPointCalculator {

  int getFreePointsSpent();

  int getPicksSpent(TraitStateType type);
}