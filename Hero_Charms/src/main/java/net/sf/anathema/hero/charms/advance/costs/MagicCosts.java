package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.magic.data.Magic;

public interface MagicCosts {

  int getMagicCosts(Magic magic, CostAnalyzer analyzer);
}
