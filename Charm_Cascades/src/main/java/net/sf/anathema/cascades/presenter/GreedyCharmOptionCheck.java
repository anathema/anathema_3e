package net.sf.anathema.cascades.presenter;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.hero.charms.model.options.CharmOptionCheck;

public class GreedyCharmOptionCheck implements CharmOptionCheck {
  @Override
  public boolean isValidOptionForHeroType(Charm charm) {
    return true;
  }
}
