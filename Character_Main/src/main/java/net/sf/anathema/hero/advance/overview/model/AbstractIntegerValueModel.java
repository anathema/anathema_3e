package net.sf.anathema.hero.advance.overview.model;

import net.sf.anathema.hero.points.display.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.display.overview.IValueModel;

public abstract class AbstractIntegerValueModel extends AbstractOverviewModel implements IValueModel<Integer> {

  public AbstractIntegerValueModel(String categoryId, String id) {
    super(categoryId, id);
  }

  @Override
  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitIntegerValueModel(this);
  }
}