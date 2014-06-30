package net.sf.anathema.hero.points.model;

public interface BonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}