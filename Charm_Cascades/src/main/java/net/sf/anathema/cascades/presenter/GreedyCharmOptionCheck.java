package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.model.options.CharmOptionCheck;
import net.sf.anathema.hero.magic.charm.Charm;

public class GreedyCharmOptionCheck implements CharmOptionCheck {
  @Override
  public boolean isValidOptionForHeroType(Charm charm) {
    return true;
  }
}
