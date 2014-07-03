package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.martial.MartialArtsLevel;
import net.sf.anathema.charm.data.martial.MartialArtsUtilities;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;

public class CharmOptionCheckImpl implements CharmOptionCheck {

  private final MartialArtsLevel standardLevel;

  public CharmOptionCheckImpl(CharmsRules rules) {
    standardLevel = rules.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public boolean isValidOptionForHeroType(Charm charm) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(charm);
    if (level != null) {
      return level.compareTo(standardLevel) <= 1;
    }
    return true;
  }
}
