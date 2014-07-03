package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.charm.data.Charm;
import net.sf.anathema.charm.data.martial.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(Charm martialArtsCharm, boolean isExperienced);

  MartialArtsLevel getStandardLevel();
}