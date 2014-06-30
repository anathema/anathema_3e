package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.points.model.BonusPointCalculator;

public interface AbilityCostCalculator extends BonusPointCalculator {

  int getFreePointsSpent(boolean favored);

  int getFavoredPicksSpent();
}