package net.sf.anathema.hero.magic.description;

import net.sf.anathema.magic.Magic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(Magic magic);
}
