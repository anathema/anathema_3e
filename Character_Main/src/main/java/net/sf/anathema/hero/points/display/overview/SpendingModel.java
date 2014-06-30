package net.sf.anathema.hero.points.display.overview;

public interface SpendingModel extends IValueModel<Integer> {

  int getSpentBonusPoints();

  int getAllotment();
}