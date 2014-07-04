package net.sf.anathema.points.display.overview.presenter;

import net.sf.anathema.library.view.StyledValueView;
import net.sf.anathema.points.model.overview.IValueModel;

public class ValueSubPresenter implements IOverviewSubPresenter {

  private final IValueModel<Integer> model;
  private final StyledValueView<Integer> view;

  public ValueSubPresenter(IValueModel<Integer> model, StyledValueView<Integer> view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void update() {
    view.setValue(model.getValue());
  }
}