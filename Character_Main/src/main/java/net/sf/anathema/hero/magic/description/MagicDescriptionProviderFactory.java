package net.sf.anathema.hero.magic.description;

import net.sf.anathema.platform.frame.ApplicationModel;

public interface MagicDescriptionProviderFactory {

  MagicDescriptionProvider create(ApplicationModel anathemaModel);
}
