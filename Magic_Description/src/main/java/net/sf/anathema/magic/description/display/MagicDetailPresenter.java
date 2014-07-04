package net.sf.anathema.magic.description.display;

import net.sf.anathema.library.presenter.Presenter;
import net.sf.anathema.magic.description.swing.IView;

public interface MagicDetailPresenter extends Presenter {

  MagicDetailModel getModel();

  String getDetailTitle();

  IView getView();
}
