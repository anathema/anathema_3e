package net.sf.anathema.hero.magic.description;

import net.sf.anathema.hero.magic.basic.Magic;

public interface MagicDescriptionProvider {

  MagicDescription getCharmDescription(Magic magic);
}
