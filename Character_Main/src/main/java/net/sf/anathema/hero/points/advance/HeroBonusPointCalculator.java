package net.sf.anathema.hero.points.advance;

public interface HeroBonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}