package net.sf.anathema.points.display.overview.model;

import net.sf.anathema.hero.points.model.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.model.overview.SpendingModel;

public abstract class AbstractSpendingModel extends AbstractOverviewModel implements SpendingModel {

  public AbstractSpendingModel(String categoryId, String id) {
    super(categoryId, id);
  }

  @Override
  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitAllotmentModel(this);
  }
}