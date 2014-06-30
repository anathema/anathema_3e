package net.sf.anathema.points.model;

public interface BonusPointCalculator {

  void recalculate();

  int getBonusPointCost();

  int getBonusPointsGranted();
}