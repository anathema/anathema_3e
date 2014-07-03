package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.Charm;

public interface CharmOptionCheck {

  boolean isValidOptionForHeroType(Charm charm);
}
