package net.sf.anathema.points.model.overview;

public interface SpendingModel extends IValueModel<Integer> {

  int getSpentBonusPoints();

  int getAllotment();
}