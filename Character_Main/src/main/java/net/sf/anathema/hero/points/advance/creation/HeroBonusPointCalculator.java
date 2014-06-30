package net.sf.anathema.hero.points.advance.creation;

public interface HeroBonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}