package net.sf.anathema.hero.points.advance.creation;

import net.sf.anathema.hero.points.model.overview.SpendingModel;

public interface IBonusPointManagement {

  void recalculate();

  SpendingModel getTotalModel();
}