package net.sf.anathema.hero.points.model;

import net.sf.anathema.hero.points.model.overview.SpendingModel;

public interface BonusPointManagement {

  void recalculate();

  SpendingModel getTotalModel();
}