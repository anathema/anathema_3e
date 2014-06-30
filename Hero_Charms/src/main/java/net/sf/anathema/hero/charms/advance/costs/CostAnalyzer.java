package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.hero.magic.basic.Magic;
import net.sf.anathema.hero.magic.charm.martial.MartialArtsLevel;

public interface CostAnalyzer {

  MartialArtsLevel getMartialArtsLevel(Magic magic);

  boolean isMagicFavored(Magic magic);

}