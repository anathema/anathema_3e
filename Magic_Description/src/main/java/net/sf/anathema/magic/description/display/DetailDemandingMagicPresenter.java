package net.sf.anathema.magic.description.display;

import net.sf.anathema.magic.description.swing.SubPresenter;

public interface DetailDemandingMagicPresenter extends SubPresenter{

  void addShowDetailListener(ShowMagicDetailListener listener);
}
