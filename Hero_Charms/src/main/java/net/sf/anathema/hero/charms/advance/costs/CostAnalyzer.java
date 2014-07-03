package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.magic.data.Magic;
import net.sf.anathema.charm.data.martial.MartialArtsLevel;

public interface CostAnalyzer {

  MartialArtsLevel getMartialArtsLevel(Magic magic);

  boolean isMagicFavored(Magic magic);

}