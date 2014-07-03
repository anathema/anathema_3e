package net.sf.anathema.magic.description.display;

import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.magic.description.swing.IView;

public interface MagicDetailPresenter extends Presenter {

  MagicDetailModel getModel();

  String getDetailTitle();

  IView getView();
}
