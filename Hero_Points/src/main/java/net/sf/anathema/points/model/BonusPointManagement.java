package net.sf.anathema.points.model;

import net.sf.anathema.points.model.overview.SpendingModel;

public interface BonusPointManagement {

  void recalculate();

  SpendingModel getTotalModel();
}