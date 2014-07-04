package net.sf.anathema.magic.description.display;

import net.sf.anathema.library.resources.Resources;
import net.sf.anathema.platform.frame.ApplicationModel;

public interface MagicDetailPresenterFactory {

  MagicDetailPresenter create(ApplicationModel anathemaModel, Resources resources);
}
