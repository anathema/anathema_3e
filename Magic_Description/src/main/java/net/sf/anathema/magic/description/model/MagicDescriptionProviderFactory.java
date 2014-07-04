package net.sf.anathema.magic.description.model;

import net.sf.anathema.platform.frame.ApplicationModel;

public interface MagicDescriptionProviderFactory {

  MagicDescriptionProvider create(ApplicationModel anathemaModel);
}
