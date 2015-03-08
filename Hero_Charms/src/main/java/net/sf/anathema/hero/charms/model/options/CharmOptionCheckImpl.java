package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.magic.data.Charm;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;

public class CharmOptionCheckImpl implements CharmOptionCheck {

  public CharmOptionCheckImpl(CharmsRules rules) {
  }

  @Override
  public boolean isValidOptionForHeroType(Charm charm) {
  	// TODO: No longer relevant?
    return true;
  }
}
