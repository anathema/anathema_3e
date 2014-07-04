package net.sf.anathema.magic.description.model;

import net.sf.anathema.magic.data.Magic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(Magic magic);
}
