package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.magic.charm.Charm;

public interface CharmOptionCheck {

  boolean isValidOptionForHeroType(Charm charm);
}
