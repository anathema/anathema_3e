package net.sf.anathema.hero.abilities.advance.creation;

import net.sf.anathema.points.model.BonusPointCalculator;

public interface AbilityCostCalculator extends BonusPointCalculator {

  int getFreePointsSpent(boolean favored);

  int getFavoredPicksSpent();
  
  int getCastePicksSpent();
  
  int getSupernalPicksSpent();
}