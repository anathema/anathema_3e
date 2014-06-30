package net.sf.anathema.hero.points.model.overview;

public interface SpendingModel extends IValueModel<Integer> {

  int getSpentBonusPoints();

  int getAllotment();
}