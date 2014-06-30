package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.hero.points.advance.creation.HeroBonusPointCalculator;

public interface AbilityCostCalculator extends HeroBonusPointCalculator {

  int getFreePointsSpent(boolean favored);

  int getFavoredPicksSpent();
}